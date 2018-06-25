package com.qmm.integrate.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by qinmengmei on 2018/6/25.
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
//@SpringBootTest(classes = RedisService.class)
public class RedisServiceTest {

    @Autowired
//    private RedisService redisService;

    @Test
    public void testHmset() throws Exception {
    }
}
