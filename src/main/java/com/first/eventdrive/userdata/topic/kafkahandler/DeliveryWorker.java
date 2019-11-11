package com.first.eventdrive.userdata.topic.kafkahandler;

import com.first.eventdrive.kafka.Consume;
import com.first.eventdrive.kafka.ProductByte;
import com.first.eventdrive.userdata.bo.DeliveryBo;
import com.first.eventdrive.userdata.topic.injection.InitTopic;
import com.first.eventdrive.userdata.topic.neums.TopicEnum;
import com.first.eventdrive.userdata.topic.utils.SpringBeanUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.serialization.kafka.ordertopic.TopicProtoBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeliveryWorker implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(DeliveryWorker.class);

    private ConsumerRecord<String, byte[]> record;

    public DeliveryWorker(ConsumerRecord<String, byte[]> record){
        this.record = record;
    }

    @Override
    public void run() {
        byte[] bytes = record.value();
        TopicProtoBuf.Topic topic = null;
        try {
            topic   =   TopicProtoBuf.Topic.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        DeliveryBo deliveryBo = (DeliveryBo) SpringBeanUtil.getBeanByClass(DeliveryBo.class);
        assert topic != null;

        deliveryBo.createDelivery(topic.getOid(), 0, "delivery Msg");

        // write
        //InitTopic initTopic = (InitTopic) SpringBeanUtil.getBeanByClass(InitTopic.class);
        ProductByte productByte = new ProductByte(TopicEnum.DELIVERY_SUCCESS.getTopic(), "success", topic.toByteArray());
        productByte.run();
        logger.info("delivery  end");
    }
}
