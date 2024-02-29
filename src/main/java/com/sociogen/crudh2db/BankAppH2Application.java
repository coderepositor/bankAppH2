package com.sociogen.crudh2db;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BankAppH2Application {

	
	
	public static void main(String[] args) {
		SpringApplication.run(BankAppH2Application.class, args);
	}

}
