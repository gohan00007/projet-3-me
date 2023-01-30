package com.ipsas.projet.authentification;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.ipsas.projet.authentification.entities.Role;
import com.ipsas.projet.authentification.entities.Utilisateur;
import com.ipsas.projet.authentification.repositories.RoleRepository;
import com.ipsas.projet.authentification.repositories.UtilisateurRepository;

@SpringBootApplication
@EnableEurekaClient
public class AuthentificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthentificationApplication.class, args);
		System.out.println("************* authentification service is running ! *************");
	}
	
	@Bean
	CommandLineRunner start(RoleRepository roleRepository, UtilisateurRepository utilisateurRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
		repositoryRestConfiguration.exposeIdsFor(Utilisateur.class, Role.class);
		
		return args -> {
			roleRepository.findAll().forEach(System.out::println);
		};
	}

}
