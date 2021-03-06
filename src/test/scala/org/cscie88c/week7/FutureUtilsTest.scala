package org.cscie88c.week7

import org.cscie88c.testutils.{ FuturesTest }
import scala.concurrent.Future
import scala.util.{ Failure, Success, Try }

class FutureUtilsTest extends FuturesTest {

  "Any future function" should {
    "return a future assertion" in {
      def futureAdd2(x: Int) = Future(x + 2)
      futureAdd2(5).map { x =>
        x shouldBe 7
      }
    }
  }

  "FutureFunctions" when {
    "calling creditScoreAPI" should {
      "return a credit score greater than 300" in {
        FutureUtils.creditScoreAPI(5).map { score: Int =>
          score should be > 300
          score should be < 800
        }
      }
    }

    "calling futureFactorial" should {
      "return the factorial of a number passed in" in {
        FutureUtils.futureFactorial(4).map(factorial => factorial should be(24))
      }
    }

    "calling futurePermutations" should {
      "return the permutations" in {
        FutureUtils.futurePermuations(4, 3).map(p => p should be(24))
        FutureUtils.futurePermuations(3, 2).map(p => p should be(6))
      }
    }

    "calling asyncAverageCreditScore" should {
      "return the average credit score" in {
        FutureUtils
          .asyncAverageCreditScore(List(1, 2, 3, 4))
          .map { score: Double =>
            score should be > 300.00
            score should be < 800.00
          }
      }
    }

    "slow multiplication" should {
      "return the correct product of two numbers" in {
        FutureUtils.slowMultiplication(5, 6) should be(30)
      }
    }

    "sequential factorial" should {

      "return the factorial" in {
        val t0 = System.nanoTime()
        FutureUtils.sequentialFactorial(3) should be(6)
        val t1 = System.nanoTime()
        println("Elapsed time: for sequential " + (t1 - t0) + "ns")
        1 should be(1)
      }

    }

    "concurrent factorial" should {

      "return the factorial" in {
        val t0 = System.nanoTime()
        FutureUtils.concurrentFactorial(3) should be(6)
        val t1 = System.nanoTime()
        println("Elapsed time:  for concurrent " + (t1 - t0) + "ns")
        1 should be(1)
      }

    }

  }
}
