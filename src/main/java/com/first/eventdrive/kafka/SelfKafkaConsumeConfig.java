package com.first.eventdrive.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class SelfKafkaConsumeConfig implements Cloneable{
    private  KafkaConsumer<String, byte[]> consumer ;

    public SelfKafkaConsumeConfig() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "test"); //consume group
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        consumer = new KafkaConsumer<>(props);
    }

    @Override
    public Object clone(){
        SelfKafkaConsumeConfig config = null;
        try {
            config = (SelfKafkaConsumeConfig)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return config;
    }

    public KafkaConsumer<String, byte[]> getConsumer() {
        return consumer;
    }
}
