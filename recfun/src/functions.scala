object Main {
    //pascal function
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

    //balance function
  def balance(chars: List[Char]): Boolean = {
    def count(chars: List[Char], open: Int): Boolean = {
      if (chars.isEmpty) {
        open == 0
      } else if (open < 0) {
        false
      } else if (chars.head == '(') {
        count(chars.tail, open + 1)
      } else if (chars.head == ')') {
        count(chars.tail, open - 1)
      } else {
        count(chars.tail, open)
      }
    }
    count(chars, 0)
  }

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
}