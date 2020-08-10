package com.xiya.demo;

import com.sankuai.meituan.util.ConfigUtilAdapter;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Properties;

@SpringBootTest
class DemoApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Test
    void test1() {
        Properties all = ConfigUtilAdapter.getAll();
        System.out.println(all);
//		ConfigUtilAdapter.getMtConfigClient();
    }

    @Test
    void test2() {
        LOGGER.error("test");
    }
}
