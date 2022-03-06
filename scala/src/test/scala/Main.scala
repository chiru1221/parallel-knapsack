import org.scalatest.funsuite.AnyFunSuite

class Main extends AnyFunSuite:
  test("normal") {
    val n: Int = 6
    val w: Int = 15
    val weights = Array(6, 5, 6, 6, 3, 7)
    val values = Array(5, 6, 4, 6, 5, 2)
    val dp = Array.ofDim[Int](n+1, w+1)
    assert(17 == normal(n, w, weights, values, dp))
  }

  test("parallel") {
    val n: Int = 6
    val w: Int = 15
    val weights = Array(6, 5, 6, 6, 3, 7)
    val values = Array(5, 6, 4, 6, 5, 2)
    val dp = Array.ofDim[Int](n+1, w+1)
    assert(17 == parallel(n, w, weights, values, dp))
  }