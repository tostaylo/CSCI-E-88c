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

      "return the correct number of characters in a invalid file" in {
        OptionUtils
          .fileCharCount(
            "not-there.csv"
          )
          .isFailure should be(true)
      }
    }

    "calling charCountAsString" should {
      "return the correct number of characters in a valid file" in {
        OptionUtils.charCountAsString(
          "src/test/resources/data/dirty-retail-data-sample.csv"
        ) should be("number of characters 186")
      }

      "return the correct number of characters in a invalid file" in {
        OptionUtils
          .charCountAsString(
            "not-there.csv"
          ) should be("error opening file")
      }
    }

    "calling lineStreamFromFile" should {
      "return the correct number of characters in a valid file" in {
        OptionUtils
          .lineStreamFromFile(
            "src/test/resources/data/dirty-retail-data-sample.csv"
          )
          .get
          .head should be("customer_id,trans_date,tran_amount")
      }
    }
  }

}
