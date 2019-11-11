package com.first.eventdrive.userdata.topic;


import com.first.eventdrive.kafka.ProductByte;
import com.first.eventdrive.userdata.topic.injection.InitTopic;
import com.first.eventdrive.userdata.topic.neums.TopicEnum;
import com.first.eventdrive.userdata.topic.utils.SpringBeanUtil;
import org.apache.kafka.streams.processor.To;
import org.serialization.kafka.ordertopic.TopicProtoBuf;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class OrderTopicGenerate {



    public void  firstWrite2Kafka(){
        InitTopic initTopic = (InitTopic) SpringBeanUtil.getBeanByClass(InitTopic.class);
        //　根据业务需要先在本地服务器的order表中写入数据
        byte[] orderByte = initTopic.getTopic().toByteArray();
        ProductByte productByte = new ProductByte(TopicEnum.ORDER_CREATE_SUCCESS.getTopic(),
                TopicEnum.ORDER_CREATE_SUCCESS.getMsg(), orderByte);
        productByte.run();
        System.out.println("write2kafka　success");
        //new Thread(productByte).start(); // 数据写入到kafka结束
    }


}
