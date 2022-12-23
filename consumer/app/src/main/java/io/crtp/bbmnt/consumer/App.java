/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package io.crtp.bbmnt.consumer;

import java.time.Duration;
import java.util.Properties;
import java.util.Arrays;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

public class App {
    public String getGreeting() {
        return "Consumer, topic kafkaDev";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());

        //Kafka consumer configuration settings
        //String topicName = args[0].toString();
        String topicName = "kafkaDev";
        Properties props = new Properties();
        //props.put("bootstrap.servers", "localhost:9092");
        props.put("bootstrap.servers", "10.10.89.95:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer",
            "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
            "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer
            <String, String>(props);
            //Kafka Consumer subscribes list of topics here.
        consumer.subscribe(Arrays.asList(topicName));
        //print the topic name
        System.out.println("Subscribed to topic " + topicName);
        int i = 0;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(60000));
            for (ConsumerRecord<String, String> record : records)
            // print the offset,key and value for the consumer records.
            System.out.printf("offset = %d, key = %s, value = %s\n",
            record.offset(), record.key(), record.value());
        }
    }
}
