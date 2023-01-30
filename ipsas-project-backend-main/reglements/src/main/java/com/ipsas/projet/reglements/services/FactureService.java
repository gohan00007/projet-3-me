package com.ipsas.projet.reglements.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.ipsas.projet.reglements.entities.Facture;

@FeignClient(name = "factures-service")
public interface FactureService {

	@GetMapping(path = "/api/factures/{id}")
	Facture findFacturetById(@PathVariable("id") Long id);
	

	@PutMapping(path = "/api/factures/{id}/montant/{montant}")
	Facture updateFacturetById(@PathVariable("id") Long id, @PathVariable("montant") float montant);
}
