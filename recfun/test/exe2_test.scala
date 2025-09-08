object Main {
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

  //output test
  def main(args: Array[String]): Unit = {
    println("balance(\"(()(()))\") = " + balance("(()(()))".toList))
    println("balance(\"(()())\") = " + balance("(()())".toList))
    println("balance(\":-)\") = " + balance(":-)".toList))
    println("balance(\"())(\") = " + balance("())(".toList))
  }
}
