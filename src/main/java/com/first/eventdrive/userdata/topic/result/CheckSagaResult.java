package com.first.eventdrive.userdata.topic.result;

import com.first.eventdrive.userdata.topic.result.redis.RedisOpear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

@Component
public class CheckSagaResult {

    @Autowired
    private JedisPool jedisPool;

   /* public Boolean hasFinish(String key){
        jedisPool.getResource().get()
        return value != null;
    }*/
}
