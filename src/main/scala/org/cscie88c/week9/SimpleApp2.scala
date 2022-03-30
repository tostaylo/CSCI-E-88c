package org.cscie88c.week9

import com.typesafe.scalalogging.LazyLogging
import scala.util.{ Failure, Success, Try }
import scala.io.Source
import org.cscie88c.config.{ ConfigUtils, SimpleApp2Config }
import pureconfig.generic.auto._

// write case class here

// run with: sbt "runMain org.cscie88c.week9.SimpleApp2"
object SimpleApp2 extends LazyLogging {

  def lineStreamFromFile(fileName: String): Option[LazyList[String]] =
    Try {
      LazyList.from(Source.fromResource(fileName).getLines())
    }.toOption

  def monthLines(month: String)(lines: LazyList[String]): LazyList[String] =
    lines.filter(_.contains(month))

  def main(args: Array[String]): Unit = {
    val simpleAppSettings =
      ConfigUtils.loadAppConfig[SimpleApp2Config]("org.cscie88c.simple-app-2")
    val month = simpleAppSettings.month;

    lineStreamFromFile(
      simpleAppSettings.fileName
    ) match {
      case Some(lines) =>
        print(
          s"Transactions for ${month}: ${monthLines(month)(lines).map(_.split(",")(2)).length} \n"
        )
      case None =>
        print(s"No Transactions found for ${month} \n")
    }

  }
}
