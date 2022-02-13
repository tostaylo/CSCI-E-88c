package org.cscie88c.week3

object UtilFunctions {

  def mult2(x: Int, y: Int): Int = x * y

  def pythTest(
      x: Int,
      y: Int,
      z: Int
    ): Boolean = {
    val xSquared = mult2(x, x)
    val ySquared = mult2(y, y)
    val zSquared = mult2(z, z)

    if (xSquared + ySquared == zSquared) {
      return true
    }

    false
  }

  val pythTriplesUpto100: List[(Int, Int, Int)] = for {
    i <- (1 to 100).toList
    j <- (1 to 100).toList
    k <- (1 to 100).toList
    if pythTest(i, j, k)
  } yield (i, j, k)

}
