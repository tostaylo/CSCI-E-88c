package org.cscie88c.week7

import scala.io.Source
import scala.util.{ Failure, Success, Try }

final case class CustomerTransaction(
    customerId: String,
    transactionDate: String,
    transactionAmount: Double
  )

object CustomerTransaction {
  def apply(csvString: String): Option[CustomerTransaction] =
    try {
      val csvToList = csvString.split(",")
      val toDouble = csvToList(2).toDouble

      if (csvToList.length != 3) throw new Exception()

      Some(
        CustomerTransaction(csvToList(0), csvToList(1), toDouble)
      )

    }
    catch {
      case e: Exception => None
    }

  def readFromCSVFile(fileName: String): List[CustomerTransaction] =
    Source
      .fromFile(fileName)
      .getLines()
      .collect { line =>
        CustomerTransaction.apply(line) match {
          case Some(customerTransaction) => customerTransaction
        }
      }
      .toList

}
