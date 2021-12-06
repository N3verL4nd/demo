package com.xiya.demo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author N3verL4nd
 * @date 2021/12/6
 */
public class RestTemplateTest extends BaseTest {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testGet() {
        ResponseEntity<String> entity = restTemplate.getForEntity("https://www.baidu.com/", String.class);
        log.info("entity: {}", entity);
    }
}
