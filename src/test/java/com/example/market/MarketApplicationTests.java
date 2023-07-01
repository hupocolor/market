package com.example.market;

import com.example.market.utils.MD5Util;
import com.example.market.utils.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MarketApplicationTests {
    @Autowired
    RedisCache redisCache;

    @Test
    void contextLoads() {
        System.out.println(String.valueOf(redisCache.getCacheObject("userid")));
    }

}
