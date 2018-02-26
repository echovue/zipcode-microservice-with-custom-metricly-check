package com.echovue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ZipcodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipcodeApplication.class, args);
	}
}
