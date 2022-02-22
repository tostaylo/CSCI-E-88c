package org.cscie88c.week4

import org.cscie88c.testutils.{ StandardTest }

class FunctionUtilsTest extends StandardTest {

  "FunctionUtils" when {
    "calling applyNtimes" should {
      "return the correct value with add5" in {
        def add5(x: Int) = x + 5
        FunctionUtils.applyNtimes(3)(0)(add5) should be(15)
        FunctionUtils.applyNtimes(3)(1)(add5) should be(16)
        FunctionUtils.applyNtimes(2)(10)(add5) should be(20)
        FunctionUtils.applyNtimes(1)(1)(add5) should be(6)
      }

      "return the correct value with power" in {
        FunctionUtils.myPositivePower(2, 2) should be(4)
        FunctionUtils.myPositivePower(2, 4) should be(16)
        FunctionUtils.myPositivePower(3, 2) should be(9)
        FunctionUtils.myPositivePower(3, 3) should be(27)
      }
    }

  }

}
