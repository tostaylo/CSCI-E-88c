package org.cscie88c.week2

object UtilFunctions {

  // complete the functions below
  def maximum(a: Int, b: Int): Int = {
    if (a > b) {
      return a
    }

    b
  }

  def average(a: Int, b: Int): Double = {
    val sum: Double = a + b
    val avg = sum / 2

    avg
  }

}
