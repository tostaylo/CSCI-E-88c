package org.cscie88c.week2

class Administrator(
    name: String,
    email: String,
    budget: Long
  ) extends UniversityEmployee(name, email) {

  override def description(): String =
    s"Name: $name, Email: $email, Budget: $budget"
}
