package com.one.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ImportResource("classpath:spring-security.xml")
@ComponentScan(basePackages = { "com" })
@EnableAutoConfiguration
@PropertySource("classpath:message.properties")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
