package com.example.demospringboot;

import com.example.demospringboot.scope.BeanLifeCycle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableCaching
public class DemoSpringbootApplication {
    public static void main(String[] args) {
        System.out.println("Truoc khi IoC Container duoc khoi tao");
        ApplicationContext context = SpringApplication.run(DemoSpringbootApplication.class, args);
        System.out.println("Truoc khi IoC Container duoc khoi tao");
        BeanLifeCycle beanLifeCycle = context.getBean(BeanLifeCycle.class);
        System.out.println(beanLifeCycle);
        System.out.println("Truoc khi IoC Container destroy bean");
        ((ConfigurableApplicationContext) context).getBeanFactory().destroyBean(beanLifeCycle);
        System.out.println(beanLifeCycle);
        System.out.println("Sau khi IoC Container destroy bean");
    }
}
