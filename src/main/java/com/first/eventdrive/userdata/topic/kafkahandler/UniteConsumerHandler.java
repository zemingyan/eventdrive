package com.first.eventdrive.userdata.topic.kafkahandler;


import com.first.eventdrive.userdata.topic.kafkahandler.demo.Worker;
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

/**
 * 可以使用工厂模式进行统一管理
 * 因为这里所有的topic数据是设计成一样的　为了减少难度，就没有用工厂模式去改写
 */

public class UniteConsumerHandler implements Runnable{
    private static Logger logger = LoggerFactory.getLogger(UniteConsumerHandler.class);


    // 本例中使用一个consumer将消息放入后端队列，你当然可以使用前一种方法中的多实例按照某张规则同时把消息放入后端队列
    private final KafkaConsumer<String, byte[]> consumer;
    private ExecutorService executors;
    private Class dealClazz;
    private Integer workerNum;

    public UniteConsumerHandler(String brokerList, String groupId, String topic, Integer workerNum, Class dealClazz) {
        this.dealClazz = dealClazz;
        this.workerNum = workerNum;
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

    @Override
    public void run() {
        executors = new ThreadPoolExecutor(workerNum, workerNum, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1000), new ThreadPoolExecutor.CallerRunsPolicy());

        while (true) {
            ConsumerRecords<String, byte[]> records = consumer.poll(200);
            for (final ConsumerRecord record : records) {
               // System.out.println("交给word处理");
                Constructor constructor = null;
                try {
                    constructor = dealClazz.getConstructor(ConsumerRecord.class);
                    logger.info("当前处理类是  " + dealClazz);
                    executors.submit((Runnable) constructor.newInstance(record));
                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void shutdown() {
        if (consumer != null) {
            consumer.close();
            Thread.State s;
        }
        if (executors != null) {
            executors.shutdown();
        }
        try {
            if (!executors.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("Timeout.... Ignore for this case");
            }
        } catch (InterruptedException ignored) {
            System.out.println("Other thread interrupted this shutdown, ignore for this case.");
            Thread.currentThread().interrupt();

        }
    }

}