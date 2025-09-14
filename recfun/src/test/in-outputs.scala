object Main {
    def main(args: Array[String]): Unit = {
        //In naver blog
        //string
        import scala.io.StdIn

        println("이름을 입력하세요:")
        val name = StdIn.readLine()
        println(s"안녕하세요, $name 님!")

        //int
        import scala.io.StdIn

        println("나이를 입력하세요:")
        val ageString = StdIn.readLine()
        val age = ageString.toInt // 문자열을 정수로 변환
        println(s"당신의 나이는 $age살 입니다.")
    }
}