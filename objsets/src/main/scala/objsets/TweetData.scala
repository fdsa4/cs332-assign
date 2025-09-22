/**
 * Represents an immutable tweet with user, text, and retweet count.
 */
class Tweet(val user: String, val text: String, val retweets: Int) {
  override def equals(that: Any): Boolean =
    that match {
      case that: Tweet => user == that.user && text == that.text && retweets == that.retweets
      case _ => false
    }
  override def toString: String = s"Tweet($user, $text, $retweets)"
  override def hashCode: Int = (user, text, retweets).hashCode()
}

/**
 * Abstract class for a linear sequence of tweets.
 */
abstract class TweetList {
  def head: Tweet
  def tail: TweetList
  def isEmpty: Boolean
  def printTweets: Unit
}

/**
 * Represents a non-empty tweet list.
 */
class Cons(val head: Tweet, val tail: TweetList) extends TweetList {
  def isEmpty = false
  def printTweets: Unit = {
    println(head)
    tail.printTweets
  }
}

/**
 * Represents an empty tweet list.
 */
class Nil extends TweetList {
  def head: Nothing = throw new NoSuchElementException("head of empty list")
  def tail: Nothing = throw new NoSuchElementException("tail of empty list")
  def isEmpty = true
  def printTweets: Unit = ()
}