package org.cscie88c.week7

import org.cscie88c.testutils.{ StandardTest }
import scala.util.{ Failure, Success, Try }

class CustomerTransactionTest extends StandardTest {
  "CustomerTransaction" should {
    "instantiate a instance from csvString" in {
      CustomerTransaction.apply("1234,10-20-2022,10.0") match {
        case Some(value) =>
          value.customerId should be("1234")
          value.transactionDate should be("10-20-2022")
          value.transactionAmount should be(10.0)
      }
    }
    "return None if csvString is improperly formatted" in {
      CustomerTransaction.apply("1234,10-20-2022,10.0x") match {
        case None => None should be(None)
      }
    }

    "clean dirty data and return the correct amount of valid customer transactions" in {
      CustomerTransaction
        .readFromCSVFile(
          "src/test/resources/data/dirty-retail-data-sample.csv"
        )
        .length should be(5)
    }
  }

}
