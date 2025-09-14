object Main {
    //pascal function
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  //output test
  def main(args: Array[String]): Unit = {
    println("pascal(0,2) = " + pascal(0,2))
    println("pascal(1,2) = " + pascal(1,2))
    println("pascal(1,3) = " + pascal(1,3))
    println("pascal(2,4) = " + pascal(2,4))
  }
}
