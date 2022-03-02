package org.cscie88c.week5

import org.cscie88c.testutils.{ StandardTest }
import Streams.Dog

class StreamsTest extends StandardTest {
  "StreamsTest" when {
    "mult5" should {
      "return multiples of 5" in {
        val it = Streams.mult5.iterator
        it.next() should be(0)
        it.next() should be(5)
        it.next() should be(10)
      }
    }

    "dogs" should {
      "contain unique dogs" in {
        val it1 = Streams.dogs.iterator
        it1.next().name should not be (it1.next().name)
      }
    }

    "healthyDogs" should {
      "only contain healthy dogs" in {
        val fiveDogs = Streams.healthyDogs(Streams.dogs).take(5)
        fiveDogs.foreach(_.hasCurrentShots should be(true))
      }
    }

    "averageHealthyAge" should {
      "calculate average age of healthy dogs" in {
        val threeHealthyDogs = Streams.dogs.filter(_.hasCurrentShots).take(3)
        val averageAge =
          (threeHealthyDogs(0).age.toDouble
            + threeHealthyDogs(1).age.toDouble
            + threeHealthyDogs(2).age.toDouble) / 3.toDouble

        Streams.averageHealthyAge(threeHealthyDogs, 3) should be(averageAge)
      }
    }
  }
}
