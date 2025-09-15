package funsets

import common._

object FunSets {


   // represents a set by its characteristic function, which is a function that
   // takes an integer and returns a boolean.
   
  type Set = Int => Boolean

  
  // indicates whether a given element is in a set.
  def contains(s: Set, elem: Int): Boolean = s(elem)

  // returns the set of the one given element.
  def singletonSet(elem: Int): Set = (x: Int) => x == elem

  // returns the union of the two given sets,
  // the set of all elements that are in either `s` or `t`.
  def union(s: Set, t: Set): Set = (x: Int) => contains(s, x) || contains(t, x)

  // returns the intersection of the two given sets,
  // the set of all elements that are both in `s` and `t`.
  def intersect(s: Set, t: Set): Set = (x: Int) => contains(s, x) && contains(t, x)

  // returns the difference of the two given sets,
  // the set of all elements of `s` that are not in `t`.
  def diff(s: Set, t: Set): Set = (x: Int) => contains(s, x) && !contains(t, x)

  // returns the subset of `s` for which `p` holds.

  def filter(s: Set, p: Int => Boolean): Set = (x: Int) => contains(s, x) && p(x)


  val bound = 1000

  // tests whether a given predicate `p` holds for all elements of `s`.
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a > bound) true
      else if (contains(s, a)) p(a) && iter(a + 1)
      else iter(a + 1)
    }
    iter(-bound)
  }

  // tests whether there exists an element in `s` that satisfies `p`.
  def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, (x: Int) => !p(x))

  // returns a set that contains all elements of `s` after applying `f` to them.
  def map(s: Set, f: Int => Int): Set = (y: Int) => exists(s, (x: Int) => f(x) == y)
}