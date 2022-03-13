package org.cscie88c.week7

import org.cscie88c.testutils.{ StandardTest }
import scala.util.{Try, Success, Failure}

class CustomerTransactionTest extends StandardTest {
  "CustomerTransaction" should {
    "load and clean raw CSV data file" in {
    CustomerTransaction.apply("1234,10-20-2022,10.0") match  {
     case Some(value) => {
        value.customerId should be("1234")
        value.transactionDate should be("10-20-2022")
        value. transactionAmount should be(10.0)
     }
     case None => None should be(Some())
     }
    }
  }

}
