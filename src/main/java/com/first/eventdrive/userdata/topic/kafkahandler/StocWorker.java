package com.first.eventdrive.userdata.topic.kafkahandler;

import com.first.eventdrive.kafka.ProductByte;
import com.first.eventdrive.userdata.bo.StocBo;
import com.first.eventdrive.userdata.topic.injection.InitTopic;
import com.first.eventdrive.userdata.topic.neums.TopicEnum;
import com.first.eventdrive.userdata.topic.utils.SpringBeanUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.serialization.kafka.ordertopic.TopicProtoBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class StocWorker implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(StocWorker.class);

    private ConsumerRecord<String, byte[]> record = null;

    public StocWorker(ConsumerRecord<String, byte[]> record){
        this.record = record;
    }

    @Override
    public void run() {
        byte[] bytes = record.value();
        TopicProtoBuf.Topic topic = null;
        try {
             topic = TopicProtoBuf.Topic.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        StocBo stocBo = (StocBo) SpringBeanUtil.getBeanByClass(StocBo.class);

        for (Map.Entry<Integer, Integer> entry : topic.getMap().entrySet()){
            stocBo.minusNumByCommondity(entry.getKey(), entry.getValue());
        }
        //　写入Ｋａｆｋａ
        //InitTopic initTopic = (InitTopic) SpringBeanUtil.getBeanByClass(InitTopic.class);
        ProductByte productByte = new ProductByte(TopicEnum.STOC_SUCCESS.getTopic(),"third success",
                topic.toByteArray());
        productByte.run();
        logger.info("stoc end");
    }
}
