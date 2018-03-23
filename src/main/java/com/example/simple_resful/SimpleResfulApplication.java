package com.example.simple_resful;

import com.example.simple_resful.MQTT.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@ServletComponentScan
@ComponentScan("com.example")
// To scan specified package for repositories
@EnableJpaRepositories("com.example.simple_resful.repository")
// To pick up our JPA entity
@EntityScan("com.example.simple_resful.models")
@SpringBootApplication
public class SimpleResfulApplication {
//
//    @Autowired
//    MessageListener messageListener;

	public static void main(String[] args) {
		SpringApplication.run(SimpleResfulApplication.class, args);
	}
//
//	@Bean
//    public CommandLineRunner schedulingRunner(TaskExecutor executor) {
//	    return new CommandLineRunner() {
//            @Override
//            public void run(String... strings) throws Exception {
//                executor.execute(messageListener);
//            }
//        };
//    }
}
