package com.example.market;

import com.example.market.utils.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MarketApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(MD5Util.md5("123456"));
    }

}
