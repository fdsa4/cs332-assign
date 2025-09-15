package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  import FunSets._

  test("contains is implemented correctly") {
    assert(contains(singletonSet(1), 1))
  }
  
  test("singletonSet(1) contains 1") {
    val s = singletonSet(1)
    assert(contains(s, 1))
    assert(!contains(s, 2))
  }

  test("union contains all elements") {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s_union = union(union(s1, s2), s3)
    
    assert(contains(s_union, 1))
    assert(contains(s_union, 2))
    assert(contains(s_union, 3))
    assert(!contains(s_union, 4))
  }

  test("intersect contains common elements") {
    val s1 = union(singletonSet(1), singletonSet(2))
    val s2 = union(singletonSet(2), singletonSet(3))
    val s_intersect = intersect(s1, s2)
    
    assert(!contains(s_intersect, 1))
    assert(contains(s_intersect, 2))
    assert(!contains(s_intersect, 3))
  }
  
  test("diff contains correct elements") {
    val s1 = union(singletonSet(1), singletonSet(2))
    val s2 = union(singletonSet(2), singletonSet(3))
    val s_diff = diff(s1, s2)
    
    assert(contains(s_diff, 1))
    assert(!contains(s_diff, 2))
    assert(!contains(s_diff, 3))
  }
  
  test("filter works correctly") {
    val s = union(union(singletonSet(1), singletonSet(2)), singletonSet(3))
    val s_filter = filter(s, (x: Int) => x > 1)
    
    assert(!contains(s_filter, 1))
    assert(contains(s_filter, 2))
    assert(contains(s_filter, 3))
  }

  test("forall works correctly") {
    val s = union(union(singletonSet(1), singletonSet(2)), singletonSet(3))
    val p1 = (x: Int) => x > 0
    val p2 = (x: Int) => x > 1
    
    assert(forall(s, p1))
    assert(!forall(s, p2))
  }
  
  test("exists works correctly") {
    val s = union(union(singletonSet(1), singletonSet(2)), singletonSet(3))
    val p1 = (x: Int) => x == 2
    val p2 = (x: Int) => x == 4
    
    assert(exists(s, p1))
    assert(!exists(s, p2))
  }

  test("map transforms elements correctly") {
    val s = union(union(singletonSet(1), singletonSet(2)), singletonSet(3))
    val mappedSet = map(s, (x: Int) => x * x)
    
    assert(!contains(mappedSet, 1))
    assert(contains(mappedSet, 4))
    assert(contains(mappedSet, 9))
    assert(!contains(mappedSet, 2))
  }
}