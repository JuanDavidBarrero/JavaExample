package com.kafka.consumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumerexample {

    private static final Logger log = LoggerFactory.getLogger(Consumerexample.class.getSimpleName());

    public static void main(String[] args) {
        log.info("I am a Kafka Consumer!");

        String groupId = "my-java-application";
        String topic = "demo_java";

        Properties properties = new Properties();

        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("group.id", groupId);
        properties.setProperty("auto.offset.reset", "earliest");

        // Create Kafka consumer
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(Arrays.asList(topic));

            // Run polling in a loop
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                log.info("Shutdown detected. Closing Kafka consumer...");
                consumer.wakeup(); // Signal to exit the polling loop
            }));

            try {
                while (true) {
                    log.info("Polling for messages...");
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

                    for (ConsumerRecord<String, String> record : records) {
                        log.info("Key: {}, Value: {}", record.key(), record.value());
                        log.info("Partition: {}, Offset: {}", record.partition(), record.offset());
                    }
                }
            } catch (Exception e) {
                log.error("Unexpected exception in Kafka consumer", e);
            } finally {
                log.info("Closing Kafka consumer...");
                consumer.close(); // Ensure proper shutdown
                log.info("Kafka consumer closed.");
            }
        }
    }
}
