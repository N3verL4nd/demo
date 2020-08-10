package com.xiya.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;


@Service
public class ApplicationContextUtils implements ApplicationContextAware {
    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    public Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

}