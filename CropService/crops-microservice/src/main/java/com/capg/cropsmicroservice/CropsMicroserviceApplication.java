package com.capg.cropsmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CropsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CropsMicroserviceApplication.class, args);
	}

}
