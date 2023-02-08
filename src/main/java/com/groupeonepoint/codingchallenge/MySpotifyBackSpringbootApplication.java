package com.groupeonepoint.codingchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class MySpotifyBackSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpotifyBackSpringbootApplication.class, args);
	}
}
