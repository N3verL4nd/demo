package com.xiya.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DemoApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplicationTests.class);


    @Test
    void test1() {
        LOGGER.error("test");
    }
}
