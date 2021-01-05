package com.covid19.app.covid19service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Covid19ServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(Covid19ServiceApplication.class, args);
	}

	@Bean
	RestTemplate getRestTemplate(RestTemplateBuilder builder){
		return builder.build();
	}

}
