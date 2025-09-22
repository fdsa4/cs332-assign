/**
 * Provides access to a predefined set of tweets and functions to filter them.
 */
object TweetReader {
  // A sample tweet set for demonstration.
  lazy val allTweets: TweetSet = {
    val set = new Empty()
      .incl(new Tweet("google_news", "Android 14 is here! The new OS is awesome.", 120))
      .incl(new Tweet("apple_fan", "Excited for the new iPhone launch event!", 250))
      .incl(new Tweet("tech_daily", "New Galaxy Foldable phone announced.", 80))
      .incl(new Tweet("random_user", "What is Scala?", 5))
      .incl(new Tweet("mac_geek", "My new Mac Studio is a beast.", 150))
    set
  }

  val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")
  val apple = List("iphone", "iPhone", "ipad", "iPad", "mac", "Mac")

  // Filters tweets mentioning Google-related keywords.
  lazy val googleTweets: TweetSet = allTweets.filter(tweet => google.exists(keyword => tweet.text.contains(keyword)))

  // Filters tweets mentioning Apple-related keywords.
  lazy val appleTweets: TweetSet = allTweets.filter(tweet => apple.exists(keyword => tweet.text.contains(keyword)))
  
  // Creates a list of trending tweets by retweets.
  lazy val trending: TweetList = googleTweets.union(appleTweets).descendingByRetweet
}