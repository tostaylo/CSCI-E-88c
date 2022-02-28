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
        // val it1 = Streams.dogs.iterator
        // val it2 = Streams.dogs.iterator

        // it1.next().name should not be (it2.next().name)
      }
    }
  }
}
