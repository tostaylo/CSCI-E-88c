package org.cscie88c.week13

import java.time.Duration
import java.util.Properties

import org.apache.kafka.streams.kstream.Materialized
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.scala.kstream._
import org.apache.kafka.streams.{ KafkaStreams, StreamsConfig }

// run with: sbt "runMain org.cscie88c.week13.KafkaStreamsApp"
object KafkaStreamsApp {

  def main(args: Array[String]): Unit = {
    import Serdes._

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
    val wordCounts: KTable[String, Long] = textLines
      .flatMapValues(textLine => textLine.toLowerCase.split("\\W+"))
      .groupBy((_, word) => word)
      .count()(Materialized.as("counts-store"))

    // 4. write the results to a topic or other persistent store
    wordCounts
      .toStream
      // .peek((k,t) => println(s"stream element: $k: $t")) // to print items in stream
      .filter((_, count) => count > 5)
      .map((word, count) => (word, s"$word: $count"))
      .to("WordsWithCountsTopic")

    // 5. start the streams application
    val streams: KafkaStreams = new KafkaStreams(builder.build(), props)
    streams.start()

    // 6. attach shutdown handler to catch control-c
    sys.ShutdownHookThread {
      streams.close(Duration.ofSeconds(10))
    }
  }

}
