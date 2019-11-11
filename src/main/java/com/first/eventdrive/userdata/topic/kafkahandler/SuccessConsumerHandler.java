
package com.first.eventdrive.userdata.topic.kafkahandler;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SuccessConsumerHandler {

    private static Logger logger = LoggerFactory.getLogger(UniteConsumerHandler.class);


    // 本例中使用一个consumer将消息放入后端队列，你当然可以使用前一种方法中的多实例按照某张规则同时把消息放入后端队列
    private final KafkaConsumer<String, byte[]> consumer;

    public SuccessConsumerHandler(String brokerList, String groupId, String topic) {
        Properties props = new Properties();
        props.put("bootstrap.servers", brokerList);
        props.put("group.id", groupId);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));
    }


    public String subScriber() {
        while (true) {
            ConsumerRecords<String, byte[]> records = consumer.poll(1);

/*  for (final ConsumerRecord record : records) {
                // System.out.println("交给word处理");
                Constructor constructor = null;
                try {
             //       constructor = dealClazz.getConstructor(ConsumerRecord.class);
                    logger.info("当前处理类是  " + this.getClass());
              //      executors.submit((Runnable) constructor.newInstance(record));
                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

            }*/

        }
    }
}

