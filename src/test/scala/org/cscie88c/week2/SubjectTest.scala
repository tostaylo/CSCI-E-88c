package org.cscie88c.week2

import _root_.org.cscie88c.testutils.StandardTest

class SubjectTest extends StandardTest {
  "Subject" when {
    "companion class" should {
      "get STEM subjects" in {
        Subject.stemSubjects should equal(
          List(
            Subject(1, "Physics", true),
            Subject(2, "Chemistry", true),
            Subject(3, "Math", true)
          )
        )
      }
    }
  }
}
