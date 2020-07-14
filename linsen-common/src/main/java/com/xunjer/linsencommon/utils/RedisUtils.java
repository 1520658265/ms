package com.xunjer.linsencommon.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/7/1417:31
 */
public class RedisUtils {
    RedisTemplate redisTemplate = new RedisTemplate();

    public void saveValue(String key, Object value){


        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        if(!redisTemplate.hasKey(key)){
            valueOperations.set(key,value);
        }
    }

    public Object getValue(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
