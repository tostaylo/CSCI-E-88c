package org.cscie88c.week10

import org.apache.spark.sql.SparkSession
import com.typesafe.scalalogging.{ LazyLogging }
import org.cscie88c.config.{ ConfigUtils }
import org.cscie88c.utils.{ SparkUtils }
import org.apache.spark.sql.{ Dataset }
import pureconfig.generic.auto._

case class SparkDSConfig(
    name: String,
    masterUrl: String,
    transactionFile: String
  )

// run with: sbt "runMain org.cscie88c.week10.SparkDSApplication"
object SparkDSApplication {

  // application main entry point
  def main(args: Array[String]): Unit = {
    implicit val conf: SparkDSConfig = readConfig()

    val spark = SparkUtils.sparkSession(conf.name, conf.masterUrl)

    val transactionDS = loadData(spark)
    val totalsByCategoryDS = transactionTotalsByCategory(spark, transactionDS)

    printTransactionTotalsByCategory(totalsByCategoryDS)
    spark.stop()
  }

  def readConfig(): SparkDSConfig = {
    val sparkConfig =
      ConfigUtils.loadAppConfig[SparkDSConfig](
        "org.cscie88c.spark-ds-application"
      )

    return sparkConfig;
  }

  def loadData(
      spark: SparkSession
    )(implicit
      conf: SparkDSConfig
    ): Dataset[CustomerTransaction] = {
    import spark.implicits._;
    spark
      .read
      .format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load(conf.transactionFile)
      .as[CustomerTransaction]
  }

  def transactionTotalsByCategory(
      spark: SparkSession,
      transactions: Dataset[CustomerTransaction]
    ): Dataset[(String, Double)] = {
    import spark.implicits._;

    transactions
      .as[CustomerTransaction]
      .map {
        case transaction if transaction.transactionAmount > 80 =>
          ("High", transaction.transactionAmount)
        case transaction => ("Standard", transaction.transactionAmount)
      }
      .groupByKey(t => t._1)
      .mapValues(_._2)
      .reduceGroups((x, y) => (x + y))
  }

  def printTransactionTotalsByCategory(
      ds: Dataset[(String, Double)]
    ): Unit = ds
    .collect
    .foreach(transaction =>
      println(s"Category: ${transaction._1}, Total: ${transaction._2} ")
    )
}
