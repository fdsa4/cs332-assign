object Main {
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

  def main(args: Array[String]): Unit = {
    val input = scala.io.StdIn.readLine()
    println(balance(input.toList))
  }
}