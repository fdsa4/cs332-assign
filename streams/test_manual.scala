import streams._
import streams.Bloxorz._

// Test Level 1
object TestLevel1 extends Level {
  val level =
  """ooo-------
    |oSoooo----
    |ooooooooo-
    |-ooooooooo
    |-----ooToo
    |------ooo-""".stripMargin
}

// Test basic functionality
println("Testing terrain function...")
println(s"Terrain at (0,0): ${TestLevel1.terrain(Pos(0,0))}")
println(s"Terrain at (4,11): ${TestLevel1.terrain(Pos(4,11))}")

println("\nTesting findChar...")
println(s"Start position: ${TestLevel1.startPos}")
println(s"Goal position: ${TestLevel1.goal}")

println("\nTesting startBlock...")
println(s"Start block: ${TestLevel1.startBlock}")

println("\nTesting isStanding...")
val standingBlock = Block(Pos(1,1), Pos(1,1))
val lyingBlock = Block(Pos(1,1), Pos(1,2))
println(s"Standing block isStanding: ${standingBlock.isStanding}")
println(s"Lying block isStanding: ${lyingBlock.isStanding}")

println("\nTesting solution...")
val solution = TestLevel1.solution
println(s"Solution: ${solution}")
println(s"Solution length: ${solution.length}")

// Verify solution
def solve(ls: List[Move], startBlock: Block): Block =
  ls.foldLeft(startBlock) { case (block, move) => move match {
    case Left => block.left
    case Right => block.right
    case Up => block.up
    case Down => block.down
  }
}

val finalBlock = solve(solution, TestLevel1.startBlock)
println(s"Final block: ${finalBlock}")
println(s"Goal block: ${Block(TestLevel1.goal, TestLevel1.goal)}")
println(s"Solution correct: ${finalBlock == Block(TestLevel1.goal, TestLevel1.goal)}")
