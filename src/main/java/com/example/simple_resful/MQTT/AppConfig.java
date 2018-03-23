package com.example.simple_resful.MQTT;

import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

/*
* The subscriber need to subscribe message continuously.
* We can achieve this by implementing TaskExecutor with the help of  Spring Boot's CommandLineRunner.
* */

@Component
public class AppConfig {

//    @Bean
//    public TaskExecutor taskExecutor() {
//        return new SimpleAsyncTaskExecutor();
//    }
}
