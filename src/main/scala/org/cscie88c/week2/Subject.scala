package org.cscie88c.week2

// complete the definition of the Subject case class and companion object
final case class Subject(
    id: Int,
    name: String,
    isSTEM: Boolean
  )

object Subject {
  val allSubjects: List[Subject] =
    List(
      Subject(1, "Physics", true),
      Subject(2, "Chemistry", true),
      Subject(3, "Math", true),
      Subject(4, "English", false)
    )

  def stemSubjects: List[Subject] =
    allSubjects.filter(subject => subject.isSTEM)

  // def apply(csvRow: String): Subject = {
  //   val fields = csvRow.split(",")

  //   Subject(
  //     id = fields(0),
  //     name = fields(1),
  //     isStem = fields(2)
  //   )
}
