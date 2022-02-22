package org.cscie88c.week4

import org.cscie88c.testutils.{ StandardTest }

class FunctionUtilsTest extends StandardTest {

  "FunctionUtils" when {
    def add5(x: Int) = x + 5

    "calling applyNtimes" should {
      "return the correct value with add5" in {
        FunctionUtils.applyNtimes(3)(0)(add5) should be(15)
        FunctionUtils.applyNtimes(3)(1)(add5) should be(16)
        FunctionUtils.applyNtimes(2)(10)(add5) should be(20)
        FunctionUtils.applyNtimes(1)(1)(add5) should be(6)
      }

    }

    "calling myPositivePower" should {
      "return the correct value with power" in {
        FunctionUtils.myPositivePower(2, 2) should be(4)
        FunctionUtils.myPositivePower(2, 4) should be(16)
        FunctionUtils.myPositivePower(3, 2) should be(9)
        FunctionUtils.myPositivePower(3, 3) should be(27)
      }
    }

    "calling deferred executor" should {
      "return the correct result and print the correct output" in {
        val myDeferredFunction =
          FunctionUtils.deferredExecutor("CPU Pool")(add5)
        val result = myDeferredFunction(4)

        result should be(9)
      }
    }
  }
}
