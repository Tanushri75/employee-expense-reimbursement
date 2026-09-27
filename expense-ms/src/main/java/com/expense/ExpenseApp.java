package com.expense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:messages.properties")
@SpringBootApplication
public class ExpenseApp {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseApp.class, args);
	}

}
