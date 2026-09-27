package com.expensemanagement.expense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@EnableFeignClients
@PropertySource("classpath:messages.properties")
public class ExpenseApp {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseApp.class, args);
	}

}
