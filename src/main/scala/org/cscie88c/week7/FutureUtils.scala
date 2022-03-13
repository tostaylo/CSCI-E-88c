package org.cscie88c.week7

import scala.concurrent.{ Future }
import scala.util.{ Failure, Success, Try }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random
import scala.collection.parallel.CollectionConverters._

object FutureUtils {

  val rnd = new Random()

  def creditScoreAPI(applicantId: Int): Future[Int] = Future {
    rnd.between(300, 800)
  }

  def printCreditScore(applicantId: Int): Unit =
    creditScoreAPI(applicantId).onComplete {
      case Success(score) => print(s"Your credit score is ${score}")
      case Failure(_: Exception) =>
        print("Cannot get credit score for ${applicantId} at this time")
    }

  def passedCreditCheck(applicantId: Int): Future[Boolean] =
    creditScoreAPI(applicantId).map { score =>
      if (score > 600) true else false
    }

  def futureFactorial(n: Int): Future[Int] = Future {
    (1 to n).foldLeft(1)(_ * _)
  }

  def futurePermuations(n: Int, r: Int): Future[Int] =
    for {
      a <- futureFactorial(n)
      b <- futureFactorial(n - r)
    } yield a / b

  def asyncAverageCreditScore(idList: List[Int]): Future[Double] = ???

  def slowMultiplication(x: Long, y: Long): Long = ???

  def concurrentFactorial(n: Long): Long = ???

  def sequentialFactorial(n: Long): Long = ???

}
