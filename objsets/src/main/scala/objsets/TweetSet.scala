import TweetReader._
import scala.collection.immutable.Set
import java.util.NoSuchElementException

/**
 * Abstract class for an immutable set of tweets.
 */
abstract class TweetSet {
  def filter(p: Tweet => Boolean): TweetSet
  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet
  def union(that: TweetSet): TweetSet
  def descendingByRetweet: TweetList
  def mostRetweeted: Tweet
  def incl(tweet: Tweet): TweetSet
  def contains(tweet: Tweet): Boolean
  def remove(tweet: Tweet): TweetSet
  def isEmpty: Boolean
  def foreach(f: Tweet => Unit): Unit
}

/**
 * Represents an empty tweet set.
 */
class Empty extends TweetSet {
  def filter(p: Tweet => Boolean): TweetSet = this
  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = acc
  def union(that: TweetSet): TweetSet = that
  def descendingByRetweet: TweetList = Nil
  def incl(tweet: Tweet): TweetSet = new NonEmpty(tweet, new Empty, new Empty)
  def contains(tweet: Tweet): Boolean = false
  def remove(tweet: Tweet): TweetSet = this
  def isEmpty: Boolean = true
  def mostRetweeted: Tweet = throw new NoSuchElementException("Empty set has no most retweeted tweet.")
  def foreach(f: Tweet => Unit): Unit = ()
}

/**
 * Represents a non-empty tweet set as a binary search tree.
 */
class NonEmpty(elem: Tweet, left: TweetSet, right: TweetSet) extends TweetSet {
  def filter(p: Tweet => Boolean): TweetSet = filterAcc(p, new Empty)
  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = {
    val newAcc = right.filterAcc(p, acc)
    if (p(elem)) left.filterAcc(p, newAcc.incl(elem))
    else left.filterAcc(p, newAcc)
  }

  def union(that: TweetSet): TweetSet = ((left union right) union that) incl elem
  
  def descendingByRetweet: TweetList = {
    val popularTweet = mostRetweeted
    new Cons(popularTweet, remove(popularTweet).descendingByRetweet)
  }
  
  def mostRetweeted: Tweet = {
    if (right.isEmpty) elem
    else right.mostRetweeted
  }

  def incl(tweet: Tweet): TweetSet = {
    if (tweet.text < elem.text) new NonEmpty(elem, left.incl(tweet), right)
    else if (tweet.text > elem.text) new NonEmpty(elem, left, right.incl(tweet))
    else this
  }
  def contains(tweet: Tweet): Boolean = {
    if (tweet.text < elem.text) left.contains(tweet)
    else if (tweet.text > elem.text) right.contains(tweet)
    else true
  }
  def remove(tweet: Tweet): TweetSet = {
    if (tweet.text < elem.text) new NonEmpty(elem, left.remove(tweet), right)
    else if (tweet.text > elem.text) new NonEmpty(elem, left, right.remove(tweet))
    else left.union(right)
  }
  def isEmpty: Boolean = false
  def foreach(f: Tweet => Unit): Unit = {
    left.foreach(f)
    f(elem)
    right.foreach(f)
  }
}