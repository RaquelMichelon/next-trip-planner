package com.rmtech.next_trip_planner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class NextTripPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NextTripPlannerApplication.class, args);
	}

}
