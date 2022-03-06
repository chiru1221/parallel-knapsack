import java.lang.Thread
class Parallel(dp: Array[Array[Int]], weights: Array[Int], values: Array[Int], i: Int, weight: Int) extends Thread:
  override def run(): Unit = 
    if weight-weights(i) >= 0 then
      if dp(i+1)(weight) < dp(i)(weight-weights(i))+values(i) then dp(i+1)(weight) = dp(i)(weight-weights(i)) + values(i)
    
    if dp(i+1)(weight) < dp(i)(weight) then dp(i+1)(weight) = dp(i)(weight)
    Thread.sleep(1)


def parallel(n: Int, w: Int, weights: Array[Int], values: Array[Int], dp: Array[Array[Int]]): Int =
  for
    i <- 0 to n-1
  do
    val process: Array[Parallel] = new Array[Parallel](w+1)
    for
      weight <- 0 to w
    do
      val p = new Parallel(dp, weights, values, i, weight)
      p.start()
      process(weight) = p
    process.foreach(p => p.join())
  dp(n)(w)

def normalUpdate(dp: Array[Array[Int]], weights: Array[Int], values: Array[Int], i: Int, weight: Int): Unit =
  if weight-weights(i) >= 0 then
    if dp(i+1)(weight) < dp(i)(weight-weights(i))+values(i) then dp(i+1)(weight) = dp(i)(weight-weights(i)) + values(i)
  
  if dp(i+1)(weight) < dp(i)(weight) then dp(i+1)(weight) = dp(i)(weight)
  Thread.sleep(1)

def normal(n: Int, w: Int, weights: Array[Int], values: Array[Int], dp: Array[Array[Int]]): Int =
  for
    i <- 0 to n-1
    weight <- 0 to w
  do
    normalUpdate(dp, weights, values, i, weight)
  dp(n)(w)

@main 
def main: Unit = 
  val n: Int = 6
  val w: Int = 15
  val weights = Array(6, 5, 6, 6, 3, 7)
  val values = Array(5, 6, 4, 6, 5, 2)
  val dp = Array.ofDim[Int](n+1, w+1)
  println(normal(n, w, weights, values, dp))
  println(parallel(n, w, weights, values, dp))

/*
> sample.txt
---
6 15
6 5
5 6
6 4
6 6
3 5
7 2
---
*/