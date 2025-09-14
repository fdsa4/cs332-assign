package recfun

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ChangeSuite extends FunSuite {
  import Main._

  test("countChange: example case") {
    assert(countChange(4, List(1, 2)) === 3)
  }

  test("countChange: no coins for 0") {
    assert(countChange(0, List(1, 2)) === 1)
  }
  
  test("countChange: no coins") {
    assert(countChange(5, List()) === 0)
  }
  
  test("countChange: large amount with multiple coins") {
    assert(countChange(10, List(1, 5, 10)) === 4)
  }
}