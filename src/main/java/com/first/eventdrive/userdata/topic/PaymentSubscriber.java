package com.first.eventdrive.userdata.topic;

import com.first.eventdrive.kafka.SelfKafkaConsumeConfig;
import com.first.eventdrive.userdata.bo.PaymentBo;
import com.first.eventdrive.userdata.topic.neums.TopicEnum;
import com.first.eventdrive.userdata.topic.utils.SpringBeanUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.serialization.kafka.ordertopic.TopicProtoBuf;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.Arrays;

public class PaymentSubscriber implements Runnable{



    public void run(){
        SelfKafkaConsumeConfig kafkaConsumeConfig = (SelfKafkaConsumeConfig) SpringBeanUtil.getBeanByClass(SelfKafkaConsumeConfig.class);
        //　深拷贝
        KafkaConsumer<String, byte[]> consumer = ((SelfKafkaConsumeConfig) kafkaConsumeConfig.clone()).getConsumer();
        consumer.subscribe(Arrays.asList(TopicEnum.ORDER_CREATE_SUCCESS.getTopic()));

        while (true){
            ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(10));
            for (ConsumerRecord<String, byte[]> record: records){
                byte[] bytes = record.value();
                TopicProtoBuf.Topic topic = null;
                try {
                    topic = TopicProtoBuf.Topic.parseFrom(bytes);
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
                // 可以根据业务优化  　　也可以异步执行

                PaymentDeal paymentDeal = new PaymentDeal(topic.getOid(), topic.getMoney(), "pay success");
                // 后期可以用统一的线程池
                new Thread(paymentDeal).start();
            }
        }
    }

}
