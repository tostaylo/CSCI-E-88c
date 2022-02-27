package org.cscie88c.week5

import org.cscie88c.testutils.{ StandardTest }
import FunctionUtils.CustomerTransaction

class FunctionUtilsTest extends StandardTest {
  "FunctionUtils" when {
    "calling colorToCode" should {
      "return the correct value for white" in {
        FunctionUtils.colorToCode("Black") should be((0, 0, 0))
        FunctionUtils.colorToCode("White") should be((255, 255, 255))
        FunctionUtils.colorToCode("lime") should be((0, 255, 0))
        FunctionUtils.colorToCode("Whites") should not be ((255, 255, 255))
      }
    }

    "calling fizzBuzzString" should {
      "return the correct value" in {
        FunctionUtils.fizzBuzzString(3) should be("Fizz")
        FunctionUtils.fizzBuzzString(5) should be("Buzz")
        FunctionUtils.fizzBuzzString(15) should be("FizzBuzz")
        FunctionUtils.fizzBuzzString(11) should be("")
      }
    }

    "calling fizzBuzz" should {
      "return the correct value" in {
        FunctionUtils.fizzBuzz(2) should be(List("1"))
        FunctionUtils.fizzBuzz(6) should be(List("1", "2", "Fizz", "4", "Buzz"))
        FunctionUtils.fizzBuzz(16) should be(
          List(
            "1",
            "2",
            "Fizz",
            "4",
            "Buzz",
            "Fizz",
            "7",
            "8",
            "Fizz",
            "Buzz",
            "11",
            "Fizz",
            "13",
            "14",
            "FizzBuzz"
          )
        )
      }
    }
    "calling tanDegrees" should {
      "return the correct value for tanDegrees" in {
        FunctionUtils.tanDegrees(45) should be(1.619 +- .001)
      }

      "return the correct value for tanDegrees only for defined values" in {
        FunctionUtils.tanDegrees.isDefinedAt(90) should be(false)
        FunctionUtils.tanDegrees.isDefinedAt(-90) should be(false)
        FunctionUtils.tanDegrees.isDefinedAt(91) should be(true)
      }
    }

    "calling totalHighValueTransactions" should {
      val transactions =
        List(
          CustomerTransaction("1", "1/1/01", 90),
          CustomerTransaction("2", "1/1/01", 101),
          CustomerTransaction("3", "1/1/01", 102)
        )
      "return the sum of high value transactions" in {
        FunctionUtils.totalHighValueTransactions(transactions) should be(203.0)
      }
    }

    "calling flip2" should {
      "flips arguments" in {
        FunctionUtils.flip2(math.pow)(5, 2) should be(32)
      }
    }

    "calling sampleList" should {
      "return first 5 elements of list of any type" in {
        FunctionUtils.sampleList(List(1, 2, 3, 4, 5, 6)) should be(
          List(1, 2, 3, 4, 5)
        )
        FunctionUtils.sampleList(List("1", "2", "3", "4", "5", "6")) should be(
          List("1", "2", "3", "4", "5")
        )
        FunctionUtils.sampleList(
          List(true, false, true, false, true, false)
        ) should be(
          List(true, false, true, false, true)
        )
      }
    }
  }
}
