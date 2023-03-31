package com.cos.pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.cos.pet"})
public class PetSitterSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetSitterSpringApplication.class, args);
	}

}
