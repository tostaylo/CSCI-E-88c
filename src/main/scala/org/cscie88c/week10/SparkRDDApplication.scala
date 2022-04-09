package org.cscie88c.week10

import org.apache.spark.sql.SparkSession
import com.typesafe.scalalogging.{ LazyLogging }
import org.cscie88c.config.{ ConfigUtils }
import org.cscie88c.utils.{ SparkUtils }
import org.apache.spark.rdd.{ RDD }
import pureconfig.generic.auto._

// write case class below
// case class SparkRDDConfig()

case class SparkRDDConfig(
    name: String,
    masterUrl: String,
    transactionFile: String
  )

// run with: sbt "runMain org.cscie88c.week10.SparkRDDApplication"
object SparkRDDApplication {

  // application entry point
  def main(args: Array[String]): Unit = {
    implicit val conf: SparkRDDConfig = readConfig() // 1. read configuration

    val spark = SparkUtils.sparkSession(
      conf.name,
      conf.masterUrl
    ) // 2. initialize spark session

    val rddLines = loadData(spark) // 3.load data
    print(
      "-------------------PART TWOOOO_____________________"
    )

    val rddTransactions = lineToTransactions(
      rddLines
    ) // 4. convert lines to transaction objects

    val yearlyTransactionsRDD = transactionsAmountsByYear(
      rddTransactions
    ) // 5. transform data

    printTransactionsAmountsByYear(yearlyTransactionsRDD) // 6. print results
    spark.stop() // 7. stop spark cluster
  }

  def readConfig(): SparkRDDConfig = {
    val sparkConfig =
      ConfigUtils.loadAppConfig[SparkRDDConfig](
        "org.cscie88c.spark-rdd-application"
      )
    print(sparkConfig)
    return sparkConfig;
  }

  def loadData(
      spark: SparkSession
    )(implicit
      conf: SparkRDDConfig
    ): RDD[String] = spark.sparkContext.textFile(conf.transactionFile)

  def lineToTransactions(lines: RDD[String]): RDD[CustomerTransaction] =
    lines.flatMap(CustomerTransaction.apply)

  def transactionsAmountsByYear(
      transactions: RDD[CustomerTransaction]
    ): RDD[(String, Double)] =
    transactions
      .map(transaction =>
        (
          transaction.transactionDate.split("-")(2),
          transaction.transactionAmount
        )
      )
      .reduceByKey(_ + _)

  def printTransactionsAmountsByYear(
      transactions: RDD[(String, Double)]
    ): Unit = transactions
    .collect
    .foreach(transaction =>
      println(s"Year: ${transaction._1}: Sum Transactions: ${transaction._2} ")
    )
}
