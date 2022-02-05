package org.cscie88c.week2

import org.cscie88c.testutils.{ StandardTest }
class UniversityEmployeeTest extends StandardTest {
  "UniversityEmployee" when {
    val employee1 = new UniversityEmployee("Todd", "todd@toddmail.com")
    val employee2 = new UniversityEmployee("Jane", "jane@janemail.com")

    "instantiated" should {
      "have a name property" in {
        employee1.name shouldBe "Todd"
        employee2.name shouldBe "Jane"
      }
      "have a email property" in {
        employee1.email shouldBe "todd@toddmail.com"
        employee2.email shouldBe "jane@janemail.com"
      }
      "have a description" in {
        employee1.description() shouldBe "Name: Todd, Email: todd@toddmail.com"
        employee2.description() shouldBe "Name: Jane, Email: jane@janemail.com"
      }
    }
  }
}
