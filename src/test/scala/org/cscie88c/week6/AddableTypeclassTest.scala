package org.cscie88c.week6

import org.cscie88c.testutils.{ StandardTest }

class AddableTypeclassTest extends StandardTest {

  "AddableAggregator" should {
    "sum a list of integers" in {
      AddableAggregator.sumWithAddable(List(1, 2, 3, 4, 5))(
        AddableTypeclass.intAddableTypeclass
      ) should be(15)

    }
    "sum a list of booleans" in {
      AddableAggregator.sumWithAddable(List(true, true))(
        AddableTypeclass.boolAddableTypeclass
      ) should be(true)

      AddableAggregator.sumWithAddable(List(true, false))(
        AddableTypeclass.boolAddableTypeclass
      ) should be(true)

      AddableAggregator.sumWithAddable(List(false, true))(
        AddableTypeclass.boolAddableTypeclass
      ) should be(true)

      AddableAggregator.sumWithAddable(List(false, false))(
        AddableTypeclass.boolAddableTypeclass
      ) should be(false)
    }
    "sum a list of employees" in {
      // add your unit tests here
    }
  }
}
