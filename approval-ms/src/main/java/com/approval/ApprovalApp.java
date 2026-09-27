package com.approval;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:messages.properties")
public class ApprovalApp {

	public static void main(String[] args) {
		SpringApplication.run(ApprovalApp.class, args);
	}

}
