package com.mongoit.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.mongoit.common.domain")
public class MongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}
}
