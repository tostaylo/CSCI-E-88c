package org.cscie88c.week3
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalacheck._

class StudentPropertyTest
    extends AnyFunSuite
       with Matchers
       with ScalaCheckPropertyChecks {

  val studentGen: Gen[Student] = for {

    name <- Gen.oneOf("Dennis", "Dee", "Frank")
    email <- Gen.oneOf("dennis@dennis.com", "dee@dee.com", "frank@frank.com")
    subject <- Gen.oneOf("Math", "English", "History")
    score <- Gen.choose[Int](0, 100)
  } yield Student(name, email, subject, score)

  // complete the student list generator below if attempting bonus problem
  // val studentListGen: Gen[List[Student]] = ???

  test("description contains name and email") {
    forAll(studentGen) { student =>
      student.description should be(
        s"name: ${student.name}, email: ${student.email}, subject: ${student.subject}, score: ${student.score}"
      )
    }
  }
}
