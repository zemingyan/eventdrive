package com.first.eventdrive.userdata.controller;

import com.first.eventdrive.userdata.topic.result.CheckSagaResult;
import com.first.eventdrive.userdata.topic.result.redis.RedisOpear;
import com.first.eventdrive.userdata.topic.utils.SpringBeanUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import org.serialization.kafka.ordertopic.TopicProtoBuf;
import org.serialization.redis.stringsotre.StringProtoBuff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RestController
public class IndexController {

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);


    @Autowired
    private JedisPool jedisPool;

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }
    @PostMapping(value = "/")
    public String postIndex(){
        return "postTest";
    }

    @GetMapping(value = "/test")
    public String test(){
        Jedis jedis = jedisPool.getResource();
        return jedis.get("a");
    }

    @GetMapping(value = "/get")
    public String get(@RequestParam(value = "key") String key) throws InvalidProtocolBufferException {
        logger.info(key);
        byte[] bytes = jedisPool.getResource().get(key.getBytes());
        logger.info("长度  "  + bytes.length);
        TopicProtoBuf.Topic topic = TopicProtoBuf.Topic.parseFrom(bytes);
        return topic.toString();
    }

}
