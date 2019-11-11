package com.first.eventdrive.userdata.topic.kafkahandler;

import com.first.eventdrive.kafka.ProductByte;
import com.first.eventdrive.userdata.bo.PaymentBo;
import com.first.eventdrive.userdata.topic.injection.InitTopic;
import com.first.eventdrive.userdata.topic.neums.TopicEnum;
import com.first.eventdrive.userdata.topic.utils.SpringBeanUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.serialization.kafka.ordertopic.TopicProtoBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentWorker implements Runnable{
    private static Logger logger = LoggerFactory.getLogger(PaymentWorker.class);

    private ConsumerRecord<String, byte[]> consumerRecord;

    public PaymentWorker(ConsumerRecord<String, byte[]> consumerRecord){
        this.consumerRecord = consumerRecord;
    }
    @Override
    public void run() {
        byte[] bytes =consumerRecord.value();
        TopicProtoBuf.Topic topic = null;
        try {
            topic   =   TopicProtoBuf.Topic.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        //InitTopic initTopic = (InitTopic) SpringBeanUtil.getBeanByClass(InitTopic.class);

        PaymentBo paymentBo = (PaymentBo) SpringBeanUtil.getBeanByClass(PaymentBo.class);
         paymentBo.createPayment(topic.getOid(), topic.getMoney(), "the describe");


        ProductByte productByte = new ProductByte(TopicEnum.PAYMENT_SUCCESS.getTopic(), "second success",
                topic.toByteArray());
        productByte.run(); // 这里需不要开线程　　run 方法原本也是普通方法
        logger.info("payment  end");
    }
}
