package com.ifsc.chatapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.ifsc.chatapi")
@EntityScan(basePackages = "com.ifsc.chatapi.model")
public class ChatApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApiApplication.class, args);
	}
}
