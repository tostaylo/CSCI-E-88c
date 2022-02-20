package org.cscie88c.week4

import org.cscie88c.testutils.{ StandardTest }

class ListUtilsTest extends StandardTest {
  "ListUtils" when {

    "initialzing a list" should {
      "return a list with the correct size and initial values" in {
        ListUtils.initDoubleList(0)(3) should be(List(0, 0, 0))
      }
    }

    "calling ones" should {
      "return the correct value" in {
        ListUtils.ones(3) should be(List(1.0, 1.0, 1.0))
      }
    }

    "calling zeros " should {
      "return the correct value" in {
        ListUtils.zeros(3) should be(List(0.0, 0.0, 0.0))
      }
    }

    "counting chars in a string " should {
      "return the correct value" in {
        val charCounts = ListUtils.charCounts("hello world")

        charCounts('h') should be(1)
        charCounts('e') should be(1)
        charCounts('l') should be(3)
        charCounts('o') should be(2)
        charCounts('w') should be(1)
        charCounts('r') should be(1)
        charCounts('d') should be(1)
        charCounts.get(' ') should be(None)

      }
    }
  }

}
