package org.cscie88c.week7

import scala.io.Source
import scala.util.{Try, Success, Failure}

final case class CustomerTransaction(
  customerId: String,
  transactionDate: String,
  transactionAmount: Double
)

object CustomerTransaction {
  def apply(csvString: String): Option[CustomerTransaction]= {
     try {
      val csvToList = csvString.split(",")
      val isDouble = csvToList(2).toDouble
      if(csvToList.length != 3) throw new Exception()
      
      Some(CustomerTransaction(csvToList(0), csvToList(1), csvToList(2).toDouble))

    } catch {
     case e: Exception => None
   }
  }
}