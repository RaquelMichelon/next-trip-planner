package com.rmtech.next_trip_planner;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NextTripPlannerApplicationTests {

	//We've asked Spring to inject a test helper that’ll allow
	// us to make HTTP requests to the locally running application.
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldReturnAPingWhenGetPingIsCalled() {
		//Here we use restTemplate to make an HTTP GET request to our application endpoint
		ResponseEntity<String> response = restTemplate.getForEntity("/ping/1", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		//assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

		//This converts the response body or string into a JSON-aware object with lots of helper methods
		DocumentContext documentContext = JsonPath.parse(response.getBody());
		String ping = documentContext.read("$.ping");
		assertThat(ping).isEqualTo("pong");

		Number id = documentContext.read("$.id");
		assertThat(id).isNotNull();
		assertThat(id).isEqualTo(1);

		//assertThat(response.getBody()).isBlank();
	}

	@Test
	void shouldNotReturnAPingWhenGetPingIsNotFound() {
		//Here we use restTemplate to make an HTTP GET request to our application endpoint
		ResponseEntity<String> response = restTemplate.getForEntity("/ping/2", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

		assertThat(response.getBody()).isBlank();
	}

}
