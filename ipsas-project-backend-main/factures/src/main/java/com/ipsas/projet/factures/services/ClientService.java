package com.ipsas.projet.factures.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.ipsas.projet.factures.entities.Client;

@FeignClient(name = "clients-service")
public interface ClientService {

	@GetMapping(path = "/api/clients/{id}")
	Client findClientById(@PathVariable("id") Long id);
}
