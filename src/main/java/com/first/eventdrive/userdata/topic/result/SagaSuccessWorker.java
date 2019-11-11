package com.first.eventdrive.userdata.topic.result;

import com.first.eventdrive.userdata.topic.result.redis.RedisOpear;
import com.first.eventdrive.userdata.topic.utils.SpringBeanUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.serialization.kafka.ordertopic.TopicProtoBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;

public class SagaSuccessWorker implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(SagaSuccessWorker.class);

    private ConsumerRecord<String, byte[]> consumerRecord;

    public SagaSuccessWorker(ConsumerRecord<String, byte[]> consumerRecord){
        this.consumerRecord = consumerRecord;
    }
    @Override
    public void run() {
        byte[] bytes = consumerRecord.value();
        TopicProtoBuf.Topic topic = null;
        try {
            topic   =   TopicProtoBuf.Topic.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        StringBuffer keyBuffer = new StringBuffer(topic.getOid());
        keyBuffer.append("_").append(topic.getUuid());

        JedisPool jedisPool = (JedisPool) SpringBeanUtil.getBeanByClass(JedisPool.class);

        String key = topic.getOid() + keyBuffer.toString();
        jedisPool.getResource().set(key.getBytes(), topic.toByteArray());
        logger.info(key + "   saga  end");
    }
}
