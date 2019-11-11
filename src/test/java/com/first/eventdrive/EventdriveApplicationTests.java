package com.first.eventdrive;

import com.first.eventdrive.kafka.ConsumeByte;
import com.first.eventdrive.kafka.ProductByte;
import com.first.eventdrive.userdata.bo.StocBo;
import com.first.eventdrive.userdata.topic.OrderTopicGenerate;
import com.first.eventdrive.userdata.topic.StocDeal;
import com.first.eventdrive.userdata.topic.neums.TopicEnum;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.serialization.kafka.ordertopic.TopicProtoBuf;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

@SpringBootTest
class EventdriveApplicationTests {

    @Test
    void contextLoads() {

    }
    @Test
    public void  init(){
        TopicProtoBuf.Topic.Builder builder = TopicProtoBuf.Topic.newBuilder();
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(2,3);
        builder.putAllMap(map);
        builder.setOid(8);
        builder.setMoney(200);
        TopicProtoBuf.Topic topic = builder.build();
        System.out.println(topic.toString());
       // topic.toByteArray();

        ProductByte product = new ProductByte("test","", topic.toByteArray());
        new Thread(product).start();
    }

    @Test
    public void sendeToKafka(){

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");

        Producer<String, Object>   producer = new KafkaProducer<>(props);


        TopicProtoBuf.Topic.Builder builder = TopicProtoBuf.Topic.newBuilder();
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(2,3);
        builder.putAllMap(map);
        builder.setOid(8);
        builder.setMoney(200);
        TopicProtoBuf.Topic topic = builder.build();
        System.out.println(topic.toString());
        System.out.println(topic.toByteArray());
        System.out.println(topic.toByteString());

        ConsumeByte consumeByte = new ConsumeByte("test2");
        new Thread(consumeByte).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        producer.send(new ProducerRecord<String, Object>("test2", "key", topic.toByteArray() ) );
        System.out.println("输入kafka完毕");
        producer.close();
    }

    @Test
    public void acceptFromKafka(){
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "test"); //consume group
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        KafkaConsumer<String, byte[]> consumer  = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("test"));


        while (true) {
            ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(10));
            for (ConsumerRecord<String, byte[]> record : records)
                System.out.printf("THeadeName  " + Thread.currentThread().getName()+
                        "  offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }
    @Test
    public void clazzTest(){
        Class clazz = StocDeal.class;
       // System.out.println(clazz.);
        /*OrderTopicGenerate orderTopicGenerate = new OrderTopicGenerate();
        orderTopicGenerate.firstWrite2Kafka();*/
        ProductByte productByte = new ProductByte(TopicEnum.ORDER_CREATE_SUCCESS.getTopic(), "", "fsdsdf".getBytes());
        productByte.run();
        AbstractQueuedSynchronizer s;
    }

    @Test
    public void  testAssert(){
        String name = null;
        assert (name != null) : "断言信息";
        Assert a;
        Exception e;
        Assert.notNull(null,"cuowu");
        Error error;

    }
}
