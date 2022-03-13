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
        }
      }
    }
  }
}
