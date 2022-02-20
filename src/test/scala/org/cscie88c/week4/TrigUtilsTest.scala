package org.cscie88c.week4

import org.cscie88c.testutils.{ StandardTest }

class TrigUtilsTest extends StandardTest {

  "TrigUtils" when {
    "calling sin" should {
      "return the correct value for 90" in {
        TrigUtils.sinDegrees(90.0) should be(.893 +- 0.001)
      }
    }

    "calling cos" should {
      "return the correct value for 90" in {
        TrigUtils.cosDegrees(90.0) should be(-0.448 +- 0.001)
      }
    }

  }
}
