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
      "create Subject from CSV" in {
        val subject = Subject.apply("4,History,false")
        subject.id should be(4)
        subject.name should be("History")
        subject.isSTEM should be(false)
      }
    }
  }
}
