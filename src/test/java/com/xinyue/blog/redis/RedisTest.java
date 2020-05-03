package com.xinyue.blog.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
//        System.out.println(stringRedisTemplate.opsForValue().get("key"));
//        stringRedisTemplate.opsForValue().set("key1", "Demo");
//        redisTemplate.opsForValue().set("key2", "Demo");
//        System.out.println(redisTemplate.opsForValue().get("key2"));
//        stringRedisTemplate.opsForZSet().add("demo", "key1", 1);
//        stringRedisTemplate.opsForZSet().add("demo", "key2", 2);
//        stringRedisTemplate.opsForZSet().add("demo", "key3", 3);
//        stringRedisTemplate.opsForZSet().incrementScore("demo", "key3")
        System.out.println(stringRedisTemplate.opsForZSet().score("demo", "key3"));
        stringRedisTemplate.opsForZSet().incrementScore("demo", "key3", 1);
        System.out.println(stringRedisTemplate.opsForZSet().score("demo", "key3"));

    }

}
