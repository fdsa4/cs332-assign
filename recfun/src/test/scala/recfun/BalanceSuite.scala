package recfun

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BalanceSuite extends FunSuite {
  import Main._

  test("balance: correct parentheses") {
    assert(balance("(()(()))".toList))
  }

  test("balance: simple case") {
    assert(balance("(()())".toList))
  }

  test("balance: empty string") {
    assert(balance("".toList))
  }
  
  test("balance: unmatched closing bracket") {
    assert(!balance(":-)".toList))
  }
  
  test("balance: incorrect order") {
    assert(!balance("())(".toList))
  }
}