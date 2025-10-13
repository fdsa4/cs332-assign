package streams

object SimpleTest {
  
  def main(args: Array[String]): Unit = {
    println("=== Testing Bloxorz Implementation ===\n")
    
    // Test Level 1
    object TestLevel1 extends Solver with StringParserTerrain {
      val level =
      """ooo-------
        |oSoooo----
        |ooooooooo-
        |-ooooooooo
        |-----ooToo
        |------ooo-""".stripMargin
    }
    
    // Test 1: Terrain function
    println("Test 1: Terrain function")
    val terrain00 = TestLevel1.terrain(Pos(0,0))
    val terrain411 = TestLevel1.terrain(Pos(4,11))
    println(s"  terrain(0,0) = $terrain00 (expected: true)")
    println(s"  terrain(4,11) = $terrain411 (expected: false)")
    assert(terrain00 == true, "terrain(0,0) should be true")
    assert(terrain411 == false, "terrain(4,11) should be false")
    println("  ✓ PASSED\n")
    
    // Test 2: Find start position
    println("Test 2: Find start position")
    val start = TestLevel1.startPos
    println(s"  startPos = $start (expected: Pos(1,1))")
    assert(start == Pos(1,1), "startPos should be Pos(1,1)")
    println("  ✓ PASSED\n")
    
    // Test 3: Find goal position
    println("Test 3: Find goal position")
    val goal = TestLevel1.goal
    println(s"  goal = $goal (expected: Pos(4,7))")
    assert(goal == Pos(4,7), "goal should be Pos(4,7)")
    println("  ✓ PASSED\n")
    
    // Test 4: Start block
    println("Test 4: Start block")
    val startBlock = TestLevel1.startBlock
    println(s"  startBlock = $startBlock (expected: Block(Pos(1,1),Pos(1,1)))")
    assert(startBlock == Block(Pos(1,1), Pos(1,1)), "startBlock incorrect")
    println("  ✓ PASSED\n")
    
    // Test 5: isStanding
    println("Test 5: isStanding")
    val standing = Block(Pos(1,1), Pos(1,1))
    val lying = Block(Pos(1,1), Pos(1,2))
    println(s"  Block(Pos(1,1),Pos(1,1)).isStanding = ${standing.isStanding} (expected: true)")
    println(s"  Block(Pos(1,1),Pos(1,2)).isStanding = ${lying.isStanding} (expected: false)")
    assert(standing.isStanding == true, "standing block should be standing")
    assert(lying.isStanding == false, "lying block should not be standing")
    println("  ✓ PASSED\n")
    
    // Test 6: isLegal
    println("Test 6: isLegal")
    val legal = Block(Pos(1,1), Pos(1,2))
    val illegal = Block(Pos(0,3), Pos(0,4))
    println(s"  Block(Pos(1,1),Pos(1,2)).isLegal = ${legal.isLegal} (expected: true)")
    println(s"  Block(Pos(0,3),Pos(0,4)).isLegal = ${illegal.isLegal} (expected: false)")
    assert(legal.isLegal == true, "legal block should be legal")
    assert(illegal.isLegal == false, "illegal block should not be legal")
    println("  ✓ PASSED\n")
    
    // Test 7: Solution
    println("Test 7: Finding solution")
    val solution = TestLevel1.solution
    println(s"  Solution: $solution")
    println(s"  Solution length: ${solution.length}")
    
    // Verify solution by applying moves
    def applyMove(block: Block, move: Move): Block = move match {
      case Left => block.left
      case Right => block.right
      case Up => block.up
      case Down => block.down
    }
    
    val finalBlock = solution.foldLeft(TestLevel1.startBlock)(applyMove)
    val goalBlock = Block(TestLevel1.goal, TestLevel1.goal)
    
    println(s"  Final block: $finalBlock")
    println(s"  Goal block: $goalBlock")
    println(s"  Solution reaches goal: ${finalBlock == goalBlock}")
    assert(finalBlock == goalBlock, "Solution should reach the goal")
    println("  ✓ PASSED\n")
    
    // Test 8: Optimal solution length
    println("Test 8: Optimal solution length")
    val optimalLength = 7
    println(s"  Solution length: ${solution.length} (optimal: $optimalLength)")
    assert(solution.length == optimalLength, s"Solution should have length $optimalLength")
    println("  ✓ PASSED\n")
    
    println("=== ALL TESTS PASSED ===")
  }
}
