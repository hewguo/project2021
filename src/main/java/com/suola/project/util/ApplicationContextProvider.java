package com.suola.project.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName ApplicationContextProvider
 * @Description TODO
 * @Author hewguo
 * @Date 2021-02-05 08:10
 * @Version 1.0
 **/
@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    /**
    * @ClassName
    * @Description //TODO 上下文对象实例
    * @Author 
    * @Date  
    * @Param 
    * @return 
    **/
    private static ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextProvider.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
