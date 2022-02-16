package org.cscie88c.week3

import org.cscie88c.testutils.{ StandardTest }

class StudentTest extends StandardTest {
  "Student Management System" when {
    "creating a student" should {
      "have properties - name, email, subject and score" in {
        val student = Student("Dennis", "dennis@dennis.com", "History", 80)
        student.email should be("dennis@dennis.com")
        student.name should be("Dennis")
        student.subject should be("History")
        student.score should be(80)
      }
    }

    "executing methods of a student" should {
      val student1 = Student("Dennis", "dennis@dennis.com", "History", 80)
      val student2 = Student("Dennis2", "dennis2@dennis.com", "History", 100)
      val student3 = Student("Dennis2", "dennis3@dennis.com", "Math", 100)

      "find average score" in {
        Student.averageScoreBySubject(
          "History",
          List(student1, student2, student3)
        ) should be(90)
      }

      "find average score of student" in {
        var inputStudent = Student("Dennis", "dennis@dennis.com", "Math", 50)
        Student.averageScoreByStudent(
          inputStudent,
          List(student1, student2, student3)
        ) should be(65)
      }

      "validate email" in {
        val student1 = Student("Dennis", "dennis@dennis.com", "History", 80)
        val student2 = Student("Dennis2", "dennis2dennis.com", "History", 100)

        Student.validateEmail(student1) should be(true)
        Student.validateEmail(student2) should be(false)
      }

    }
  }
}
