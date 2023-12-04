package com.study.todocard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class TodocardApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodocardApplication.class, args);
	}

}
