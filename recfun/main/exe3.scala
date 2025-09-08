object Main {
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) {
      1
    } else if (money < 0 || coins.isEmpty) {
      0
    } else {
      countChange(money - coins.head, coins) + countChange(money, coins.tail)
    }
  }

  def main(args: Array[String]): Unit = {
    val money = scala.io.StdIn.readInt()
    val coinsInput = scala.io.StdIn.readLine()
    val coins = coinsInput.split(" ").map(_.toInt).toList
    println(countChange(money, coins))
  }
}