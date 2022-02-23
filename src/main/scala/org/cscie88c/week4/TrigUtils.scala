package org.cscie88c.week4
import scala.math._

object TrigUtils {

  val sinDegrees: Double => Double = (deg: Double) => sin(deg)
  val cosDegrees: Double => Double = (deg: Double) => cos(deg)

  val squared: Double => Double = Math.pow(_, 2)

}
