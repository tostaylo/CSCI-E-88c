package org.cscie88c.week3

final case class Student(
    name: String,
    email: String,
    subject: String,
    score: Int
  ) {
  def description: String =
    s"name: ${name}, email: ${email}, subject: ${subject}, score: ${score}"
}

object Student {

  def validateEmail(student: Student): Boolean = {
    if (student.email contains "@") {
      return true;
    }
    return false;
  }

  def averageScoreBySubject(
      subject: String,
      studentList: List[Student]
    ): Double = {
    val studentsBySubject =
      studentList.filter(student => student.subject == subject)

    val sum =
      studentsBySubject.foldLeft(0)((acc, student) => acc + student.score)

    (sum / studentsBySubject.length).toDouble;
  }

  // def averageScoreByStudent(
  //     student: Student,
  //     studentList: List[Student]
  //   ): Double = ???
}
