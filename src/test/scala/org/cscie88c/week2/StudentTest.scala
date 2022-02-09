package org.cscie88c.week2

import _root_.org.cscie88c.testutils.StandardTest

class StudentTest extends StandardTest {
  "Student" when {

    "companion class" should {
      "get names of students by country" in {
        Student.studentNamesByCountry("United States") should equal(
          List("Marin Blasoni", "Delmore Scriver")
        )
      }

      "get number of students per country" in {
        Student.studentTotalsByCountry("United States") should equal(2)
      }

      "create Studentt from CSV" in {
        val student =
          Student.apply(6, "Dee", "Reynolds", "dee@dee.com", "Female", "Mexico")
        student.id should be(6)
        student.firstName should be("Dee")
        student.lastName should be("Reynolds")
        student.email should be("dee@dee.com")
        student.gender should be("Female")
        student.country should be("Mexico")
      }

    }
  }
}
