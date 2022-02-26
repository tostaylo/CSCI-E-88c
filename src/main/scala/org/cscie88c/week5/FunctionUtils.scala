package org.cscie88c.week5

import scala.math

object FunctionUtils {

  case class CustomerTransaction(
      customerId: String,
      transactionDate: String,
      transactionAmount: Double
    )

  // Problem 1
  def colorToCode(color: String): (Int, Int, Int) = color.toLowerCase() match {
    case "black"  => (0, 0, 0)
    case "white"  => (255, 255, 255)
    case "red"    => (255, 0, 0)
    case "lime"   => (0, 255, 0)
    case "blue"   => (0, 0, 255)
    case "yellow" => (255, 255, 0)
    case _        => (-1, -1, -1)
  }

  def fizzBuzzString(n: Int): String = n match {
    case n if n % 3 == 0 && n % 5 > 0 => "Fizz"
    case n if n % 5 == 0 && n % 3 > 0 => "Buzz"
    case n if n % 15 == 0             => "FizzBuzz"
    case _                            => ""
  }

  def fizzBuzz(n: Int): List[String] =
    List.range(1, n).map { x =>
      val result = fizzBuzzString(x)
      if (!result.isEmpty()) result else x.toString()
    }

  // Problem 2
  def tanDegrees: PartialFunction[Double, Double] = ???

  def totalHighValueTransactions(
      transactionList: List[CustomerTransaction]
    ): Double = ???

  // Problem 3
  def flip2[A, B, C](f: (A, B) => C): (B, A) => C = ???

  // Write a generic function sampleList parameterized by type A, that returns the first 5 elements of a list of type A.

}
