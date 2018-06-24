package com.burton.lanbitou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.burton.common.domain")
public class LanbitouApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanbitouApplication.class, args);
	}
}
