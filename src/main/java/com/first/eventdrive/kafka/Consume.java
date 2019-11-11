package com.first.eventdrive.kafka;

import org.apache.kafka.clients.KafkaClient;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class Consume extends Thread{
    private String topic;
    public Consume(String topic){
        this.topic = topic;
    }
    private static  KafkaConsumer<String, String> consumer ;
    /*public static void main(String[]args){
        KafkaClient kafkaClient;
        Consume consume = new Consume();
        Thread thread = new Thread(consume,"first");
        thread.start();
    }*/
    public void init(){
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "test"); //consume group
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));
    }
    public void consumeAuto(){
        init();
        System.out.println("init end");
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10));
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("THeadeName  " + Thread.currentThread().getName()+
                        "  offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }
    public void consumeManual(){
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "false");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("topicFirst", "my-topic"));
        final int minBatchSize = 200;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                buffer.add(record);
                System.out.println(record.key()+"  获取数据　　" + record.value());
            }
            if (buffer.size() >= minBatchSize) {
                //insertIntoDb(buffer);
                consumer.commitSync();
                buffer.clear();
            }
        }
    }

    @Override
    public void run() {
        consumeAuto();
    }
}
