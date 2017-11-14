package com.depromeet.hay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class HayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HayApplication.class, args);
	}
}
