package org.cscie88c.week6

final case class Employee(
    name: String,
    age: Int,
    salary: Int
  )

object Employee {

  implicit val employeeOrdering: Ordering[Employee] = new Ordering[Employee] {

    def compare(x: Employee, y: Employee): Int = x.name compare y.name
  }

  def defaultSortEmployees(employees: List[Employee]): List[Employee] =
    employees.sorted

  def sortEmployeesBySalary(employees: List[Employee]): List[Employee] = {
    implicit val employeeOrdering: Ordering[Employee] =
      Ordering.by[Employee, Int](_.salary)

    employees.sorted
  }

  implicit val employeeAddableTypeclass: AddableTypeclass[Employee] =
    new AddableTypeclass[Employee] {

      def addTwoValues(a: Employee, b: Employee): Employee =
        Employee(s"${a.name} ${b.name}", a.age + b.age, a.salary + b.salary)
    }

}
