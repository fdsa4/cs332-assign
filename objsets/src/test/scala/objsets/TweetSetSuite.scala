//> using dep org.scalatest::scalatest::3.2.18

import org.scalatest.funsuite.AnyFunSuite
import TweetReader._

/**
 * Test suite to verify the functionality of TweetSet methods.
 */
class TweetSetSuite extends AnyFunSuite {
  val tweet1 = new Tweet("userA", "Android is great", 20)
  val tweet2 = new Tweet("userB", "iPhone rocks", 50)
  val tweet3 = new Tweet("userC", "My Mac is good", 10)
  val tweet4 = new Tweet("userD", "Google's new Nexus phone", 30)
  val set = new Empty().incl(tweet1).incl(tweet2).incl(tweet3).incl(tweet4)

  test("filter: should filter tweets correctly based on a predicate") {
    val filteredSet = set.filter(t => t.retweets > 25)
    assert(filteredSet.contains(tweet2))
    assert(filteredSet.contains(tweet4))
    assert(!filteredSet.contains(tweet1))
    assert(!filteredSet.contains(tweet3))
  }
  
  test("union: should combine two sets correctly without duplicates") {
    val setA = new Empty().incl(tweet1)
    val setB = new Empty().incl(tweet2)
    val unionSet = setA.union(setB)
    assert(unionSet.contains(tweet1) && unionSet.contains(tweet2))
  }
  
  test("descendingByRetweet: should sort tweets by retweet count in descending order") {
    val sortedList = set.descendingByRetweet
    assert(sortedList.head.retweets == 50)
    assert(sortedList.tail.head.retweets == 30)
    assert(sortedList.tail.tail.head.retweets == 20)
    assert(sortedList.tail.tail.tail.head.retweets == 10)
  }

  test("mostRetweeted: should find the tweet with the highest retweet count") {
    assert(set.mostRetweeted.retweets == 50)
  }
}