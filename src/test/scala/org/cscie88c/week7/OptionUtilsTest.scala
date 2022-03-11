package org.cscie88c.week7

import org.cscie88c.testutils.{ StandardTest }
import scala.util.{ Failure, Success, Try }

class OptionUtilsTest extends StandardTest {
  "OptionUtils" when {
    "calling fileCharCount" should {
      "return the correct number of characters in a valid file" in {
        OptionUtils.fileCharCount(
          "src/test/resources/data/dirty-retail-data-sample.csv"
        ) should be(Success(186))
      }
    }
  }

}
