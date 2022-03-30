package org.cscie88c.week9

import com.typesafe.scalalogging.LazyLogging
import scala.util.{ Failure, Success, Try }
import scala.io.Source

// run using: sbt "runMain org.cscie88c.week9.SimpleApp1 <args>"
object SimpleApp1 extends LazyLogging {

  def lineStreamFromFile(fileName: String): Option[LazyList[String]] =
    Try {
      LazyList.from(Source.fromResource(fileName).getLines())
    }.toOption

  def monthLines(month: String)(lines: LazyList[String]): LazyList[String] =
    lines.filter(_.contains(month))

  def main(args: Array[String]): Unit = lineStreamFromFile(
    "data/Retail_Data_Transactionss.csv"
  ) match {
    case Some(value) =>
      print(
        s"Transactions for Jan: ${monthLines("Jan")(value).map(_.split(",")(2)).length} \n"
      )
    case None =>
      print("No Transactions found for Jan \n")
  }

}
