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

  implicit class CSVWrapper(val prod: Product) extends AnyVal {
    def toCSV() = prod
      .productIterator
      .map {
        case Some(value) => value
        case None        => ""
        case rest        => rest
      }
      .mkString(",")
  }

  def writeFile(filename: String, lines: List[String]): Unit = {
    val file = new File(filename)
    val bw = new BufferedWriter(new FileWriter(file))

    for (line <- lines)
      bw.write(s"${line}\n")

    bw.close()
  }

  def main(args: Array[String]): Unit = {
    import Serdes._

    val props: Properties = {
      val p = new Properties()
      p.put(StreamsConfig.APPLICATION_ID_CONFIG, "song-recommender-application")
      p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
      p
    }

    val builder: StreamsBuilder = new StreamsBuilder
    val textLines: KStream[String, String] =
      builder.stream[String, String]("TextLinesTopic")

    val rawSongs = Song
      .readFromCSVFile(
        "src/main/resources/data/spotify_songs.csv"
      )

    val csvHeader = rawSongs(0)

    // the first Song is the top row of the csv
    val songs = rawSongs
      .drop(1)

    textLines.foreach { (count, songId) =>
      writeFile(
        "src/main/resources/data/input-topics.log.txt",
        List(s"count: ${count} ||| id:  ${songId}")
      )

      val foundSongs = songs.filter(_.id == songId)
      if (foundSongs.length > 0) {

        val foundSong = foundSongs(0)

        val sameGenreSongs =
          songs.filter(song =>
            song.playlistGenre == foundSong.playlistGenre && song.playlistSubGenre == foundSong.playlistSubGenre
          )

        // If this is accurate the first song should always be the input song since there should be a difference of 0 in attribute values
        val similarSongs =
          foundSong
            .rankSimilarSongs(sameGenreSongs)
            .filter(_._1 != foundSong.id)

        val recommendedSong =
          sameGenreSongs.find(_.id == similarSongs(0)._1).get

        writeFile(
          "src/main/resources/data/recommended_song.csv",
          List(csvHeader.toCSV(), recommendedSong.toCSV(), foundSong.toCSV())
        )
      }
      else {
        // Error Logging can be moved to cloud architecture
        writeFile(
          "src/main/resources/data/errors.log.txt",
          List(s"Song id: ${songId} was not found")
        )
      }
    }

    val streams: KafkaStreams = new KafkaStreams(builder.build(), props)
    streams.start()

    sys.ShutdownHookThread {
      streams.close(Duration.ofSeconds(10))
    }

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
  ) {

  def average(count: Double): Song = Song(
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
    (danceability.toDouble / count).toString(),
    (energy.toDouble / count).toString(),
    (key.toDouble / count).toString(),
    (loudness.toDouble / count).toString(),
    (mode.toDouble / count).toString(),
    (speechiness.toDouble / count).toString(),
    (acousticness.toDouble / count).toString(),
    (instrumentalness.toDouble / count).toString(),
    (liveness.toDouble / count).toString(),
    (valence.toDouble / count).toString(),
    (tempo.toDouble / count).toString(),
    ""
  )

  def rankSimilarSongs(songs: List[Song]): List[(String, Double)] =
    songs
      .map { song =>
        val sum =
          (danceability.toDouble - song
            .danceability
            .toDouble).abs + (energy.toDouble - song
            .energy
            .toDouble).abs + (energy.toDouble - song
            .energy
            .toDouble).abs + (key.toDouble - song
            .key
            .toDouble).abs + (loudness.toDouble - song
            .loudness
            .toDouble).abs + (mode.toDouble - song
            .mode
            .toDouble).abs + (speechiness.toDouble - song
            .speechiness
            .toDouble).abs + (acousticness.toDouble - song
            .acousticness
            .toDouble).abs + (instrumentalness.toDouble - song
            .instrumentalness
            .toDouble).abs + (liveness.toDouble - song
            .liveness
            .toDouble).abs + (valence.toDouble - song
            .valence
            .toDouble).abs + (tempo.toDouble - song.tempo.toDouble).abs

        (song.id, sum)

      }
      .sortBy(_._2)

}

object Song {
  def apply(csvString: String): Option[Song] =
    try {
      val csvToList = csvString.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")
      if (csvToList.length != 23) {
        // need to print error to console here
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

// the key to this application will be building a  graph which will have the closest songs in similarity in the neighboring nodes.  But also a binary search tree for matching the song quickly to begin with. Will bloom filter help?

// Useful if we want to identify metrics for each genre which deviate from average song

// def attributeDiffsByGenre(
//     averageSongsBySubgenre: Map[String, Song],
//     averageSongAttributePairs: Map[String, Any]
//   ): Map[String, List[(String, Double)]] = averageSongsBySubgenre.map {
//   case (key, value) =>
//     val genre = key;
//     val subgenreAverageSong = value;

//     val subgenreAverageSongMap =
//       (subgenreAverageSong.productElementNames zip subgenreAverageSong.productIterator).toMap

//     var attributeDiffs: List[(String, Double)] = List()

//     averageSongAttributePairs.foreach { kv =>
//       val key = kv._1
//       val vl = kv._2
//       val subgenreAttributeVal = subgenreAverageSongMap(key)

//       if (subgenreAttributeVal != "") {
//         val diff =
//           (subgenreAttributeVal.toString().toDouble - vl
//             .toString()
//             .toDouble).abs

//         attributeDiffs = attributeDiffs.appended((key, diff))
//       }

//     }

//     (genre, attributeDiffs.sortBy(_._2));
// }

// val averageSong =
//   songs.reduce(_ |+| _).average(songs.length.toDouble)

// val averageSongAttributePairs =
//   (averageSong.productElementNames zip averageSong.productIterator).toMap

// val averageSongsBySubgenre =
//   songs
//     .groupBy(_.playlistSubGenre)
//     .map {
//       case (subgenre, songs) =>
//         (subgenre, songs.reduce(_ |+| _).average(songs.length.toDouble))
//     }
