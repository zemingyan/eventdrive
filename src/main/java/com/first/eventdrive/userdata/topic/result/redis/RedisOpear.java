package com.first.eventdrive.userdata.topic.result.redis;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

@Deprecated
@Component
public class RedisOpear {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public Boolean expire(String key, Long time){
       try{
           if (time > 0){
               redisTemplate.expire(key, time, TimeUnit.SECONDS);
           }
           return true;
       }catch (Exception e ){
           e.printStackTrace();
           return false;
       }
    }

    public Long getExpire(String key){
        return redisTemplate.getExpire(key);
    }

    public Boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        }catch (Exception e ){
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public void delete(String... key){
        if (key != null && key.length > 0){
            if (key.length == 1){
                redisTemplate.delete(key[0]);
            }else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    public Object get(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public Boolean set(String key, Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean set(String key, Object value, Long time){
        try {
            if (time > 0){
                redisTemplate.opsForValue().set(key, value, time);
            }else{
                set(key, value);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }



}

