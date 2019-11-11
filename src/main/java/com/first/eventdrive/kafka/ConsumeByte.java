package com.first.eventdrive.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.serialization.kafka.ordertopic.TopicProtoBuf;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ConsumeByte extends Thread{
    private String topic;
    public ConsumeByte(String topic){
        this.topic = topic;
    }
    private static  KafkaConsumer<String, byte[]> consumer ;

    public void init(){
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "test"); //consume group
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));
    }
    public void consumeAuto(){
        init();
        System.out.println("init end");
        while (true) {
            ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(10));
            for (ConsumerRecord<String, byte[]> record : records) {
                TopicProtoBuf.Topic topic2 =null;
                try {
                    topic2 = TopicProtoBuf.Topic.parseFrom(record.value());
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
                System.out.println("读取kafka信息  " + topic2.getMoney());
                System.out.printf("THeadeName  " + Thread.currentThread().getName()+
                        "  offset = %d, key = %s, value = %s%n", record.offset(), record.key(), topic2.toString());
            }
        }
    }


    @Override
    public void run() {
        consumeAuto();
    }
}
