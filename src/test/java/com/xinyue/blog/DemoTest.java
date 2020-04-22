package com.xinyue.blog;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sangz
 * @date 2019/9/2 21:37
 */
public class DemoTest {
    @Test
    public void testConcurrentHashMap() {
        Map<String, String> testConcurrentHashMap = new ConcurrentHashMap<>();
        testConcurrentHashMap.put("Aa", "demo");
        for (int i = 0; i < 2048; i++) {
            testConcurrentHashMap.put("Aa" + i, "demo");
        }
        testConcurrentHashMap.put("BB", "demo");
        System.out.println(testConcurrentHashMap);
    }

    @Test
    public void testOperation() {
        System.out.println(32 >>> 2);
    }
}
