package org.cscie88c.week2

import _root_.org.cscie88c.testutils.StandardTest

class FacultyTest extends StandardTest {
  "Faculty" when {
    val faculty1 = new Faculty("Todd", "todd@toddmail.com", "History")
    val faculty2 = new Faculty("Jane", "jane@janemail.com", "Math")

    "instantiated" should {
      "have a name property" in {
        faculty1.name shouldBe "Todd"
        faculty2.name shouldBe "Jane"
      }
      "have a email property" in {
        faculty1.email shouldBe "todd@toddmail.com"
        faculty2.email shouldBe "jane@janemail.com"
      }
      "have a description" in {
        faculty1.description() shouldBe "Name: Todd, Email: todd@toddmail.com, Course: History"
        faculty2.description() shouldBe "Name: Jane, Email: jane@janemail.com, Course: Math"
      }
    }
  }

}
