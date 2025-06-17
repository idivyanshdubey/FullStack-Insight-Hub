package com.example.activemqListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms

public class ActivemqListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqListenerApplication.class, args);
	}

}
