package com.first.eventdrive.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProductByte implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(ProductByte.class);

    private static  Producer<String, byte[]> producer;
    private byte[] msg;
    private String key;
    private String topic;

    public ProductByte(String topic,String key, byte[] msg){
        this.msg = msg;
        this.key = key;
        this.topic = topic;
    }

    public void init(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");

        producer = new KafkaProducer<>(props);
    }
    public void createFirst(){
        producer.send(new ProducerRecord<String, byte[]>(topic, key, msg) );
        logger.info("输入kafka完毕");
        //producer.close();
    }


    @Override
    public void run() {
        init();
        createFirst();
    }
}
