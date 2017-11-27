package com.jxlg.app.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * 静态注入中间类
 * 完成RedisCache.jedisConnectionFactory的静态注入
 * @author zhouboxi
 * @create 2017-11-27 13:39
 **/
public class RedisCacheTransfer {

    @Autowired
    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
    }
}

