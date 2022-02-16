package org.cscie88c.week3

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalacheck._

class UtilFunctionsPropertyTest
    extends AnyFunSuite
       with Matchers
       with ScalaCheckPropertyChecks {

  val triplesGen: Gen[(Int, Int, Int)] =
    Gen.oneOf(UtilFunctions.pythTriplesUpto100)

  test("mult2 result test") {
    forAll { (x: Int, y: Int) =>
      UtilFunctions.mult2(x, y) shouldBe x * y
    }
  }

  test("mult2 distributive property test") {
    forAll { (x: Int, y: Int, z: Int) =>
      val one = UtilFunctions.mult2(x, y + z)
      val two = UtilFunctions.mult2(x, y) + UtilFunctions.mult2(x, z)

      one should be(two)
    }
  }

  test(
    " If (x, y, z) is a pythagorean triple, then (y, x, z) is also a pythagorean triple "
  ) {
    forAll(triplesGen) { triple =>
      UtilFunctions.pythTest(triple._1, triple._2, triple._3) should be(
        UtilFunctions.pythTest(triple._2, triple._1, triple._3)
      )
    }
  }

  test(
    "If (x, y, z) is a pythagorean triple, then (2y, 2x, 2z) is also a pythagorean triple  "
  ) {
    forAll(triplesGen) { triple =>
      UtilFunctions.pythTest(triple._1, triple._2, triple._3) should be(
        UtilFunctions.pythTest(2 * triple._2, 2 * triple._1, 2 * triple._3)
      )
    }
  }
}
