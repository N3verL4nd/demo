package com.xiya.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class BaseTest {
    protected static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @Test
    public void test() {
        log.error("基类测试");
    }
}
