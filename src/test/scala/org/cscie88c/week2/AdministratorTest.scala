package org.cscie88c.week2

import _root_.org.cscie88c.testutils.StandardTest

class AdministratorTest extends StandardTest {
  "Administrator" when {
    val admin1 = new Administrator("Todd", "todd@toddmail.com", 1000)
    val admin2 = new Administrator("Jane", "jane@janemail.com", 15000)

    "instantiated" should {
      "have a name property" in {
        admin1.name shouldBe "Todd"
        admin2.name shouldBe "Jane"
      }
      "have a email property" in {
        admin1.email shouldBe "todd@toddmail.com"
        admin2.email shouldBe "jane@janemail.com"
      }
      "have a description" in {
        admin1.description() shouldBe "Name: Todd, Email: todd@toddmail.com, Budget: 1000"
        admin2.description() shouldBe "Name: Jane, Email: jane@janemail.com, Budget: 15000"
      }
    }
  }

}
