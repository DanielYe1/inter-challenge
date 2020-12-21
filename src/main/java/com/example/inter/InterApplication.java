package com.example.inter;

import com.example.inter.repository.MongoDBBeforeSaveEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InterApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterApplication.class, args);
	}

	@Bean
	public MongoDBBeforeSaveEventListener mongoDBBeforeSaveEventListener() {
		return new MongoDBBeforeSaveEventListener();
	}
}
