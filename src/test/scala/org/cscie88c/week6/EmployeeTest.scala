package org.cscie88c.week6

import org.cscie88c.testutils.{ StandardTest }

class EmployeeTest extends StandardTest {
  "Employee" should {
    val employees = List(
      Employee("Frank", 60, 200),
      Employee("Dee", 36, 250),
      Employee("Mac", 38, 210),
      Employee("Dennis", 40, 201),
      Employee("Charlie", 40, 190)
    )

    "have a default sort order" in {

      Employee.defaultSortEmployees(employees) should be(
        List(
          Employee("Charlie", 40, 190),
          Employee("Dee", 36, 250),
          Employee("Dennis", 40, 201),
          Employee("Frank", 60, 200),
          Employee("Mac", 38, 210)
        )
      )
    }

    "sort employees by salary" in {
      Employee.sortEmployeesBySalary(employees) should be(
        List(
          Employee("Charlie", 40, 190),
          Employee("Frank", 60, 200),
          Employee("Dennis", 40, 201),
          Employee("Mac", 38, 210),
          Employee("Dee", 36, 250)
        )
      )
    }
  }
}
