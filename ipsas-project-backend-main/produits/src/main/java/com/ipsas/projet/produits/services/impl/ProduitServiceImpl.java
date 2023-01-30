package com.ipsas.projet.produits.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.ipsas.projet.produits.entities.Produit;
import com.ipsas.projet.produits.entities.enumeration.TypeTaxe;
import com.ipsas.projet.produits.repositories.ProduitRepository;
import com.ipsas.projet.produits.services.ProduitService;

@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    ProduitRepository produitRepository;

    @Override
    public Produit save(Produit produit) {
        // TODO Auto-generated method stub
    	float tva = 0;
    	if(produit.getTaxe().getType().equals(TypeTaxe.POURCENTAGE)) {
    		tva = ( produit.getPrixHT() / 100 ) * produit.getTaxe().getValeur();
    	} else {
    		tva = produit.getTaxe().getValeur();
    	}
    	
    	produit.setPrixTVA(tva);
    	produit.setPrixTTC( produit.getPrixHT() + tva );
    	
        return this.produitRepository.save(produit);
    }

    @Override
    public Produit update(Long id, Produit produit) {
        // TODO Auto-generated method stub
    	float tva = 0;
    	if(produit.getTaxe().getType().equals(TypeTaxe.POURCENTAGE)) {
    		tva = ( produit.getPrixHT() / 100 ) * produit.getTaxe().getValeur();
    	} else {
    		tva = produit.getTaxe().getValeur();
    	}
    	
    	produit.setPrixTVA(tva);
    	produit.setPrixTTC( produit.getPrixHT() + tva );
        produit.setId(id);
        return this.produitRepository.save(produit);
    }

    @Override
    public Page<Produit> findAll(int pageNo, int pageSize, String sortBy) {
        // TODO Auto-generated method stub
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return this.produitRepository.findAll(pageable);
    }

    @Override
    public Optional<Produit> findOne(Long id) {
        // TODO Auto-generated method stub
        return this.produitRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        this.produitRepository.deleteById(id);
    }

	@Override
	public List<Produit> getAllZeroStock() {
		// TODO Auto-generated method stub
		return this.produitRepository.getAllZeroStock();
	}

}
