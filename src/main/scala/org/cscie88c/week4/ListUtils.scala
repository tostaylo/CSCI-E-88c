package org.cscie88c.week4

object ListUtils {
  // complete the function below
  def initDoubleList(initValue: Double)(size: Int): List[Double] =
    List.fill(size)(initValue)

  // complete the functions below using currying
  def ones: Int => List[Double] = initDoubleList(1.0) _
  def zeros: Int => List[Double] = initDoubleList(0.0) _

  // // complete the functions below
  // def charCounts(sentence: String): Map[Char, Int] = ???

  // def topN(n: Int)(frequencies: Map[Char, Int]): Map[Char, Int] = ???

}
