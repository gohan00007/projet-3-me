package com.ipsas.projet.clients;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import com.ipsas.projet.clients.entities.Client;
import com.ipsas.projet.clients.repositories.ClientRepository;

@SpringBootApplication
@EnableEurekaClient
public class ClientsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientsApplication.class, args);
		System.out.println("************* clients service is running ! *************");
	}

	@Bean
	CommandLineRunner start(ClientRepository clientRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
		repositoryRestConfiguration.exposeIdsFor(Client.class);
		
		return args -> {
			clientRepository.findAll().forEach(System.out::println);
		};
	}
}
