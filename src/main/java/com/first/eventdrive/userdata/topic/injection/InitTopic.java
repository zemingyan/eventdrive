package com.first.eventdrive.userdata.topic.injection;

import com.sun.org.apache.xml.internal.security.Init;
import org.omg.PortableInterceptor.INACTIVE;
import org.serialization.kafka.ordertopic.TopicProtoBuf;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 前期所有的topic放入信息齐全的统一的对象  具体细节后期根据业务拆分
 * 这里不方便通过方法的+ @Bean注解返回　　可能Topic Builder在其他的地方要用，可能产生歧义
 */
@Component
public class InitTopic { //
    private TopicProtoBuf.Topic topic;
    public InitTopic(){
        TopicProtoBuf.Topic.Builder builder = TopicProtoBuf.Topic.newBuilder();
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1,2);
        map.put(2,3);
        builder.putAllMap(map);
        builder.setOid(8);
        builder.setMoney(200);
        this.topic = builder.build();
    }


    public TopicProtoBuf.Topic getTopic() {
        return topic;
    }
}
