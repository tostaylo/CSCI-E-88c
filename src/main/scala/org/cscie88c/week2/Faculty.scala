package org.cscie88c.week2

class Faculty(
    name: String,
    email: String,
    course: String
  ) extends UniversityEmployee(name, email) {

  override def description(): String =
    s"Name: $name, Email: $email, Course: $course"
}
