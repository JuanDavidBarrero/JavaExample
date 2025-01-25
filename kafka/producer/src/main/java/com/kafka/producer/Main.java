package com.kafka.producer;

import java.util.Properties;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class.getSimpleName());

    public static void main(String[] args) {
        log.info("I am a Kafka Producer!");

        // Kafka producer properties
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        // Create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // Send 100 messages
        for (int i = 1; i <= 100; i++) {
            String topic = "demo_java";
            String value = "Hello World " + i;

            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, value);

            // Send the message with a callback
            producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e == null) {
                        // Message was successfully sent
                        log.info("Message sent successfully!\n" +
                                "Topic: " + metadata.topic() + "\n" +
                                "Partition: " + metadata.partition() + "\n" +
                                "Offset: " + metadata.offset() + "\n" +
                                "Timestamp: " + metadata.timestamp());
                    } else {
                        // An error occurred
                        log.error("Error while producing message", e);
                    }
                }
            });
        }

        // Flush and close the producer
        producer.flush();
        producer.close();
    }
}
