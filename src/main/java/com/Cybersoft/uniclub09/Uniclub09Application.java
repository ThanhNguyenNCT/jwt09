package com.Cybersoft.uniclub09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Uniclub09Application {

	public static void main(String[] args) {
		SpringApplication.run(Uniclub09Application.class, args);
	}

}