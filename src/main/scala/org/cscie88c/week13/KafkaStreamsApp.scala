package org.cscie88c.week13

import java.time.Duration
import java.util.Properties

import org.apache.kafka.streams.kstream.Materialized
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.scala.kstream._
import org.apache.kafka.streams.{ KafkaStreams, StreamsConfig }
import java.io._

// run with: sbt "runMain org.cscie88c.week13.KafkaStreamsApp"
object KafkaStreamsApp {

  def main(args: Array[String]): Unit = {
    import Serdes._

    val songs = Song
      .readFromCSVFile(
        "src/main/resources/data/spotify_songs.csv"
      )

    // 1. define kafka streams properties, usually from a config file
    val props: Properties = {
      val p = new Properties()
      p.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application")
      p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
      p
    }

    // 2. create KStreams DSL instance
    val builder: StreamsBuilder = new StreamsBuilder
    val textLines: KStream[String, String] =
      builder.stream[String, String]("TextLinesTopic")

    // 3. transform the data
    // val wordCounts: KTable[String, Long] = textLines
    //   .flatMapValues(textLine => textLine.toLowerCase.split("\\W+"))
    //   .groupBy((_, word) => word)
    //   .count()(Materialized.as("counts-store"))

    // val wordCounts: KTable[String, Long] = textLines
    //   .flatMapValues(textLine => textLine.toLowerCase.split("\\W+"))
    //   .groupBy((_, word) => word)
    //   .count()(Materialized.as("counts-store"))

    // 4. write the results to a topic or other persistent store
    // wordCounts
    //   .toStream
    //   // .peek((k,t) => println(s"stream element: $k: $t")) // to print items in stream
    //   .filter((_, count) => count > 5)
    //   .map((word, count) => (word, s"$word: $count"))
    //   .to("WordsWithCountsTopic")

    songs.foreach(println)

    /** write a `String` to the `filename`.
      */
    def writeFile(filename: String, s: String): Unit = {
      val file = new File(filename)
      val bw = new BufferedWriter(new FileWriter(file))
      bw.write(s)
      bw.close()
    }
    print(System.getProperty("user.dir"))
    writeFile("src/main/resources/data/myfile.txt", "bye bye")

    // 5. start the streams application
    val streams: KafkaStreams = new KafkaStreams(builder.build(), props)
    streams.start()

    // 6. attach shutdown handler to catch control-c
    // sys.ShutdownHookThread {
    //   streams.close(Duration.ofSeconds(10))
    // }

    streams.close(Duration.ofSeconds(10))
  }

}

import scala.io.Source
import scala.util.{ Failure, Success, Try }

final case class Song(
    id: String,
    name: String,
    artist: String
  )

object Song {
  def apply(csvString: String): Option[Song] =
    try {
      val csvToList = csvString.split(",")

      Some(
        Song(csvToList(0), csvToList(1), csvToList(2))
      )

    }
    catch {
      case e: Exception => None
    }

  def readFromCSVFile(fileName: String): List[Song] =
    Source
      .fromFile(fileName)
      .getLines()
      .collect { line =>
        Song.apply(line) match {
          case Some(song) => song
        }
      }
      .toList

}
