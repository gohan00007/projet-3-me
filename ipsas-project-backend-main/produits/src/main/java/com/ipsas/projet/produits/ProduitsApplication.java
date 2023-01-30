package com.ipsas.projet.produits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProduitsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProduitsApplication.class, args);
        System.out.println("************* produits service is running ! *************");
    }

}
