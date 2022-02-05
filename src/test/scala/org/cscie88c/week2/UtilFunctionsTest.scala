package org.cscie88c.week2

import org.cscie88c.testutils.{ StandardTest }

class UtilFunctionsTest extends StandardTest {

  "UtilFunctions" when {
    "maximum" should {
      "return maximum of two ints when first integer is greater" in {
        UtilFunctions.maximum(2, 1) should be(2)
      }
      "return maximum of two ints when second integer is greater" in {
        UtilFunctions.maximum(1, 2) should be(2)
      }
      "return maximum of two ints when integers are equal" in {
        UtilFunctions.maximum(2, 2) should be(2)
      }
    }

    "average" should {
      "average of two integers case 1" in {
        UtilFunctions.average(2, 2) should be(2)
      }
      "average of two integers case 2" in {
        UtilFunctions.average(1, 2) should be(1.5)
      }
      "average of two integers case 3" in {
        UtilFunctions.average(5, 10) should be(7.5)
      }

    }

  }
}
