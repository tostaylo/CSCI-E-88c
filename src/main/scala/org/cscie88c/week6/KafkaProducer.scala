package org.cscie88c.week6

trait KafkaProducer {
  def send(message: String): String
}

case class SimpleKafkaProducer(topic: String) extends KafkaProducer {

  def send(message: String): String = s"Topic:${topic} Message:${message}"
}

object SimpleKafkaProducer {
  implicit val defaultKafkaProducer: SimpleKafkaProducer =
    new SimpleKafkaProducer("default-topic")
}

// uncomment the lines below once you have implemented KafkaProducer and SimpleKafkaProducer

object KafkaClient {
  // sends a status message to kafka
  def sendStatusEvent(status: String)(implicit kafkaProducer: KafkaProducer) =
    kafkaProducer.send(status) // use the implicit KafkaProducer provided
}
