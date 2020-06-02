package com.hcl.flightbookingapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FlightbookingapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightbookingapplicationApplication.class, args);
	}

}
