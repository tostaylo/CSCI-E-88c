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

    println("AARDVARK")
    val transactionDS = loadData(spark)
    transactionDS.show
    // val totalsByCategoryDS = transactionTotalsByCategory(spark, transactionDS)
    // printTransactionTotalsByCategory(totalsByCategoryDS)
    // spark.stop()
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
    ): Dataset[(String, Double)] = transactions.groupByKey()

//   def printTransactionTotalsByCategory(ds: Dataset[(String, Double)]): Unit = ???
}
