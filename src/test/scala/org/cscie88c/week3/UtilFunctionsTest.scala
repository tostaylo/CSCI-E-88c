package org.cscie88c.week3

import org.cscie88c.testutils.{ StandardTest }

class UtilFunctionsTest extends StandardTest {
  "UtilFunctions" when {

    "with pythTest" should {
      "verify elements are pythagorean triples" in {
        UtilFunctions.pythTest(3, 4, 5) should be(true)
        UtilFunctions.pythTest(5, 12, 13) should be(true)
        UtilFunctions.pythTest(1, 2, 3) should be(false)
      }
    }
    "with pythTriplesUpto100" should {
      "verify elements in list are pythagorean triples" in {
        val triples = UtilFunctions.pythTriplesUpto100
        for ((x, y, z) <- triples)
          UtilFunctions.pythTest(x, y, z) should be(true)
      }
    }
  }
}
