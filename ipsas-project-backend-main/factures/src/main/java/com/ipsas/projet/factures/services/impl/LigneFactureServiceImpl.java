package com.ipsas.projet.factures.services.impl;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.ipsas.projet.factures.entities.LigneFacture;
import com.ipsas.projet.factures.entities.Produit;
import com.ipsas.projet.factures.repositories.LigneFactureRepository;
import com.ipsas.projet.factures.services.LigneFactureService;
import com.ipsas.projet.factures.services.ProduitService;

@Service
@Transactional
public class LigneFactureServiceImpl implements LigneFactureService {

    @Autowired
    LigneFactureRepository ligneFactureRepository;
    
    @Autowired
    ProduitService produitService;

    @Override
    public LigneFacture save(LigneFacture ligneFacture) {
        // TODO Auto-generated method stub
    	Produit produit = this.produitService.findProduitById( ligneFacture.getProduitId() );
    	
    	ligneFacture.setPrixHT( ligneFacture.getQuantite() * produit.getPrixHT() );
    	ligneFacture.setPrixTVA( ligneFacture.getQuantite() * produit.getPrixTVA() );
    	ligneFacture.setPrixTTC( ligneFacture.getQuantite() * produit.getPrixTTC() );
        return this.ligneFactureRepository.save(ligneFacture);
    }

    @Override
    public LigneFacture update(Long id, LigneFacture ligneFacture) {
        // TODO Auto-generated method stub
    	Produit produit = this.produitService.findProduitById( ligneFacture.getProduitId() );
    	
    	ligneFacture.setPrixHT( ligneFacture.getQuantite() * produit.getPrixHT() );
    	ligneFacture.setPrixTVA( ligneFacture.getQuantite() * produit.getPrixTVA() );
    	ligneFacture.setPrixTTC( ligneFacture.getQuantite() * produit.getPrixTTC() );
        ligneFacture.setId(id);
        return this.ligneFactureRepository.save(ligneFacture);
    }

    @Override
    public Page<LigneFacture> findAll(int pageNo, int pageSize, String sortBy) {
        // TODO Auto-generated method stub
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        
        Page<LigneFacture> lignes = this.ligneFactureRepository.findAll(pageable);
        lignes.forEach(ligne -> {
        	Produit produit = this.produitService.findProduitById( ligne.getProduitId() );
        	ligne.setProduit(produit);
        });
        return lignes;
    }

    @Override
    public Optional<LigneFacture> findOne(Long id) {
        // TODO Auto-generated method stub
    	Optional<LigneFacture> ligne = this.ligneFactureRepository.findById(id);
    	Produit produit = this.produitService.findProduitById(ligne.get().getProduitId());
    	ligne.get().setProduit(produit);
        return ligne;
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        this.ligneFactureRepository.deleteById(id);
    }

}
