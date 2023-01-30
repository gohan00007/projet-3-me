package com.ipsas.projet.factures.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.ipsas.projet.factures.entities.Produit;

@FeignClient(name = "produits-service")
public interface ProduitService {

	@GetMapping(path = "/api/produits/{id}")
	public Produit findProduitById(@PathVariable("id") Long id);
}
