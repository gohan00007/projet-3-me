package com.ipsas.projet.factures.services.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.ipsas.projet.factures.entities.Client;
import com.ipsas.projet.factures.entities.Facture;
import com.ipsas.projet.factures.entities.LigneFacture;
import com.ipsas.projet.factures.entities.Produit;
import com.ipsas.projet.factures.repositories.FactureRepository;
import com.ipsas.projet.factures.services.ClientService;
import com.ipsas.projet.factures.services.FactureService;
import com.ipsas.projet.factures.services.LigneFactureService;
import com.ipsas.projet.factures.services.ProduitService;

@Service
@Transactional
public class FactureServiceImpl implements FactureService {

    @Autowired
    FactureRepository factureRepository;
    
    @Autowired
    ClientService clientService;
    
    @Autowired
    ProduitService produitService;
    
    @Autowired
    LigneFactureService ligneFactureService;

    @Override
    public Facture save(Facture facture) {
        // TODO Auto-generated method stub
    	
    	facture.getLignes().stream().forEach(ligne -> {
    		ligne.setId(null);
    	});
	   	 facture.setReste(facture.getPrixTTC());

    	Collection<LigneFacture> ligneFactures = facture.getLignes();
	   	 Facture saved = this.factureRepository.save(facture);
	   	
	   	ligneFactures.forEach(ligne -> {
	   		ligne.setFacture(saved);
	   		this.ligneFactureService.save(ligne);
	   	});
        return saved;
    }

    @Override
    public Facture update(Long id, Facture facture) {
        // TODO Auto-generated method stub
    	Facture old = this.factureRepository.getOne(id);
    	
    	old.setReste( old.getReste() - 100 );
        return this.factureRepository.save(facture);
    }
    
    public Facture updateReste(Long id, float montant ) {
        // TODO Auto-generated method stub
    	Facture old = this.factureRepository.getOne(id);
    	
    	old.setReste( old.getReste() - montant );
        return this.factureRepository.save(old);
    }

    @Override
    public Page<Facture> findAll(int pageNo, int pageSize, String sortBy) {
        // TODO Auto-generated method stub
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        
        Page<Facture> factures = this.factureRepository.findAll(pageable);
        factures.forEach(facture -> {
        	Client client = this.clientService.findClientById( facture.getClientId() );
        	facture.setClient(client);
        	
        	facture.getLignes().forEach(ligne -> {
        		Produit produit = this.produitService.findProduitById(ligne.getProduitId());
        		ligne.setProduit(produit);
        	});
        	
        });
        
    	

        return this.factureRepository.findAll(pageable);
    }

    @Override
    public Optional<Facture> findOne(Long id) {
        // TODO Auto-generated method stub
    	Optional<Facture> facture = this.factureRepository.findById(id);
    	Client client = this.clientService.findClientById( facture.get().getClientId() );
    	facture.get().setClient(client);
    	
    	facture.get().getLignes().forEach(ligne -> {
    		Produit produit = this.produitService.findProduitById(ligne.getProduitId());
    		ligne.setProduit(produit);
    	});
        return facture;
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        this.factureRepository.deleteById(id);
    }

	@Override
	public float clientChiffreAffaireGlobale(long clientId) {
		// TODO Auto-generated method stub
		Float result = this.factureRepository.getAllAffairesByClient(clientId);
		
		return (result != null) ? result : 0;
	}

	@Override
	public float clientChiffreAffaireByAnnee(long clientId, int annee) {
		// TODO Auto-generated method stub
		Float result = this.factureRepository.getAllAffairesByClientAndYear(clientId, annee);
		return (result != null) ? result : 0;
	}

	@Override
	public float clientReste(long clientId) {
		// TODO Auto-generated method stub
		Float result = this.factureRepository.getClientUnpaid(clientId);;
		return (result != null) ? result : 0;
	}

	@Override
	public List<Facture> clientPayee(long clientId) {
		// TODO Auto-generated method stub
		return this.factureRepository.getAllClientPaid(clientId);
	}

	@Override
	public List<Facture> clientNonPayee(long clientId) {
		// TODO Auto-generated method stub
		return this.factureRepository.getAllClientUnpaid(clientId);
	}

	@Override
	public List<Facture> allPayee() {
		// TODO Auto-generated method stub
		return this.factureRepository.getAllPaid();
	}

	@Override
	public List<Facture> allNonPayee() {
		// TODO Auto-generated method stub
		return this.factureRepository.getAllUnpaid();
	}

}
