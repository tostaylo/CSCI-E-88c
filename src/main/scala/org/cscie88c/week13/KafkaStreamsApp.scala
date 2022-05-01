package org.cscie88c.week13

import java.time.Duration
import java.util.Properties
import java.io._
import scala.io.Source
import scala.util.{ Failure, Success, Try }
import org.apache.kafka.streams.kstream.Materialized
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.scala.kstream._
import org.apache.kafka.streams.{ KafkaStreams, StreamsConfig }

import cats._
import cats.implicits._

// run with: sbt "runMain org.cscie88c.week13.KafkaStreamsApp"
object KafkaStreamsApp {

  def writeFile(filename: String, s: String): Unit = {
    val file = new File(filename)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(s)
    bw.close()
  }

  def main(args: Array[String]): Unit = {
    import Serdes._

    // the key to this application will be building a  graph which will have the closest songs in similarity in the neighboring nodes.  But also a binary search tree for matching the song quickly to begin with. Will bloom filter help?
    // 1. Get average of all properties of a song. Print to file.
    // 2. Get all songs of same genre and subgenre
    // 3. Determine 1 attribute which deviates furthest from average.
    // 4. Get Song input and find the attribute value.
    // 5. Find another song in the same genre and sub genre of songs which equals that value. Return song.

    // 1. define kafka streams properties, usually from a config file
    val props: Properties = {
      val p = new Properties()
      p.put(StreamsConfig.APPLICATION_ID_CONFIG, "song-recommender-application")
      p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
      p
    }

    // 2. create KStreams DSL instance
    val builder: StreamsBuilder = new StreamsBuilder
    val textLines: KStream[String, String] =
      builder.stream[String, String]("TextLinesTopic")

    textLines.peek((k, t) => println(s"stream element: $k: $t"))

    // textLines.foreach((line1, line2) =>
    //   writeFile(
    //     "src/main/resources/data/myfile.txt",
    //     s"1st thing: ${line1} ||| second thing:  ${line2}"
    //   )
    // )

    val inputSong = "7mitXLIMCflkhZiD34uEQI"
    val songs = Song
      .readFromCSVFile(
        "src/main/resources/data/spotify_songs.csv"
      )

    println(songs.length)
    println("songs.length")

    // the first Song is the top row of the csv
    val averageSong = songs.drop(1).reduce(_ |+| _)

    writeFile(
      "src/main/resources/data/average_song.txt",
      averageSong.toString()
    )

    // val foundSong = songs.filter(_.id == inputSong)(0)

    // val sameGenreSongs =
    //   songs.filter(song =>
    //     song.playlistGenre == foundSong.playlistGenre && song.playlistSubGenre == foundSong.playlistSubGenre
    //   )

    // 3. transform the data
    // val wordCounts: KTable[String, Long] = textLines
    //   .flatMapValues(textLine => textLine.toLowerCase.split("\\W+"))
    //   .groupBy((_, word) => word)
    //   .count()(Materialized.as("counts-store"))

    // 4. write the results to a topic or other persistent store
    // .toStream
    // // .peek((k,t) => println(s"stream element: $k: $t")) // to print items in stream
    // .filter((_, count) => count > 5)
    // .map((word, count) => (word, s"$word: $count"))
    // .to("WordsWithCountsTopic")

    // sameGenreSongs.foreach(song =>
    //   println(
    //     s"Artist: ${song.artist} | Song: ${song.name} | Genre: ${song.playlistGenre} | Sub-genre: ${song.playlistSubGenre}"
    //   )
    // )

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

final case class Song(
    id: String,
    name: String,
    artist: String,
    popularity: String,
    albumId: String,
    albumName: String,
    albumReleaseDate: String,
    playlistName: String,
    playlistId: String,
    playlistGenre: String,
    playlistSubGenre: String,
    danceability: String,
    energy: String,
    key: String,
    loudness: String,
    mode: String,
    speechiness: String,
    acousticness: String,
    instrumentalness: String,
    liveness: String,
    valence: String,
    tempo: String,
    duration: String
  )

object Song {
  def apply(csvString: String): Option[Song] =
    try {
      val csvToList = csvString.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")
      if (csvToList.length != 23) {
        println(csvToList(1))
      }

      Some(
        Song(
          csvToList(0),
          csvToList(1),
          csvToList(2),
          csvToList(3),
          csvToList(4),
          csvToList(5),
          csvToList(6),
          csvToList(7),
          csvToList(8),
          csvToList(9),
          csvToList(10),
          csvToList(11),
          csvToList(12),
          csvToList(13),
          csvToList(14),
          csvToList(15),
          csvToList(16),
          csvToList(17),
          csvToList(18),
          csvToList(19),
          csvToList(20),
          csvToList(21),
          csvToList(22)
        )
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

  implicit val averageSongMonoid: Monoid[Song] =
    new Monoid[Song] {
      override def empty: Song = Song.apply("").get

      override def combine(x: Song, y: Song): Song =
        Song(
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          (x.danceability.toDouble + y.danceability.toDouble).toString(),
          (x.energy.toDouble + y.energy.toDouble).toString(),
          (x.key.toDouble + y.key.toDouble).toString(),
          (x.loudness.toDouble + y.loudness.toDouble).toString(),
          (x.mode.toDouble + y.mode.toDouble).toString(),
          (x.speechiness.toDouble + y.speechiness.toDouble).toString(),
          (x.acousticness.toDouble + y.acousticness.toDouble).toString(),
          (x.instrumentalness.toDouble + y.instrumentalness.toDouble)
            .toString(),
          (x.liveness.toDouble + y.liveness.toDouble).toString(),
          (x.valence.toDouble + y.valence.toDouble).toString(),
          (x.tempo.toDouble + y.tempo.toDouble).toString(),
          ""
        )
    }

}
