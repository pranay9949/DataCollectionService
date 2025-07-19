package com.example.DataCollectionService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DataCollectionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataCollectionServiceApplication.class, args);
	}

}
