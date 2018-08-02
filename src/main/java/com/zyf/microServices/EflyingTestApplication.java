package com.zyf.microServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EflyingTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(EflyingTestApplication.class, args);
	}
}
