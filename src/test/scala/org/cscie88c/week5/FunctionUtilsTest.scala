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
        // write unit tests here
      }
    }

    "calling fizzBuzz" should {
      "return the correct value" in {
        // write unit tests here
      }
    }

    // Problem 2 unit tests

    // Problem 3 unit tests

    // Bonus unit tests
  }

}
