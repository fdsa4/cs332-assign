object Main {
    //pascal function
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  //input and output(by)
  def main(args: Array[String]): Unit = {
    val c = scala.io.StdIn.readInt()
    val r = scala.io.StdIn.readInt()
    
    //filter invalid input
    if (c < 0 || r < 0 || c > r) {
      println("Invalid input")
    } else {
      println(pascal(c, r))
    }
  }
}

