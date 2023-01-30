package com.ipsas.projet.reglements;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ReglementsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReglementsApplication.class, args);
		System.out.println("************* reglements service is running ! *************");
	}

}
