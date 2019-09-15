package com.walmart.gls.rd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableMongoRepositories
@SpringBootApplication
public class RolloutDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(RolloutDashboardApplication.class, args);
	}

}
