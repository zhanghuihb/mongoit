package com.burton.lts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.burton.common.domain")
public class LtsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LtsApplication.class, args);
	}
}
