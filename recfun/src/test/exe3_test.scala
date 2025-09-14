object Main {
    //countChange function
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) {
      1
    } else if (money < 0 || coins.isEmpty) {
      0
    } else {
      countChange(money - coins.head, coins) + countChange(money, coins.tail)
    }
  }

  //output test
  def main(args: Array[String]): Unit = {
    println("countChange(4, List(1,2)) = " + countChange(4, List(1,2)))
    println("countChange(0, List(1,2)) = " + countChange(0, List(1,2)))
    println("countChange(5, List()) = " + countChange(5, List()))
    println("countChange(10, List(1,5,10)) = " + countChange(10, List(1,5,10)))
  }
}
