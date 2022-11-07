package com.greatlearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@SpringBootApplication
public class DoConnectChatServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoConnectChatServiceApplication.class, args);
	}

}
