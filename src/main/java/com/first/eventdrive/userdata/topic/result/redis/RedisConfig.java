package com.first.eventdrive.userdata.topic.result.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 *　这种序列化，对于json格式来说直接替代jdk默认的序列化工具
 * 但是对于proto来说，序列化的时候可以指定 T extend Message 但是反序列化的时候，就不只一个class，换句话说，
 * 等于用extend   super 来充当容器，有一端是废掉了的(要么是废弃set,要么废弃get)
 * proto 最为方便的是直接存二进制，而且数据量比json小很多，　内存占用少，通过jedis操作更加方便
 */
@Deprecated
@Configuration
public class RedisConfig {
    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);


        /*Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);*/

        ProtobufRedisSerializer protobufRedisSerializer = new ProtobufRedisSerializer(Message.class);


        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式 json or proto
        template.setValueSerializer(protobufRedisSerializer);
        // hash的value序列化方式   json or proto
        template.setHashValueSerializer(protobufRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
