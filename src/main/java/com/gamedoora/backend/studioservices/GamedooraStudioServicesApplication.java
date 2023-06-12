package com.gamedoora.backend.studioservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = LiquibaseAutoConfiguration.class)
public class GamedooraStudioServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamedooraStudioServicesApplication.class, args);
	}

}
