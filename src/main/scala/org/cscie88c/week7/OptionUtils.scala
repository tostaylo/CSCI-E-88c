package org.cscie88c.week7

import scala.io.Source
import scala.util.{ Failure, Success, Try }

object OptionUtils {

  def fileCharCount(fileName: String): Try[Long] =
    Try {
      var count = 0;
      for (line <- Source.fromFile(fileName).getLines)
        for (_ <- line)
          count += 1
      count
    }

  def charCountAsString(fileName: String): String = Try {
    fileCharCount(fileName)
  } match {
    case Success(value) => s"number of characters ${value}"
    case Failure(_)     => "error opening file";
  }

  def lineStreamFromFile(fileName: String): Option[LazyList[String]] = {
      var ll: LazyList[String] = LazyList()
      for (line <- Source.fromFile(fileName).getLines){
        ll = ll.appended(line)
      }
        Some(ll)
   }
}
