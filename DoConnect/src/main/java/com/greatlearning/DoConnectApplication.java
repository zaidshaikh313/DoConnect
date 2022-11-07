package com.greatlearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@SpringBootApplication
public class DoConnectApplication {

    public static void main(String[] args) {

		SpringApplication.run(DoConnectApplication.class, args);
    }

}
