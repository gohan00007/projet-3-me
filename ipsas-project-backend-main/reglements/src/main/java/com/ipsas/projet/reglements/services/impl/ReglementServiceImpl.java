package com.ipsas.projet.reglements.services.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ipsas.projet.reglements.entities.Facture;
import com.ipsas.projet.reglements.entities.Reglement;
import com.ipsas.projet.reglements.repositories.ReglementRepository;
import com.ipsas.projet.reglements.services.FactureService;
import com.ipsas.projet.reglements.services.ReglementService;

@Service
@Transactional
public class ReglementServiceImpl implements ReglementService {
	
	@Autowired
	ReglementRepository reglementRepository;
	
	@Autowired
	FactureService factureService;

	@Override
	public Reglement save(Reglement reglement) {
		// TODO Auto-generated method stub
		Facture facture = this.factureService.findFacturetById(reglement.getFactureId());
		facture.setReste( facture.getReste() - reglement.getMontant() );
		this.factureService.updateFacturetById(facture.getId(), reglement.getMontant());
		System.out.println(facture);
		return this.reglementRepository.save(reglement);
	}

	@Override
	public Reglement update(Long id, Reglement reglement) {
		// TODO Auto-generated method stub
		reglement.setId(id);
		return this.reglementRepository.save(reglement);
	}

	@Override
	public Page<Reglement> findAll(int pageNo, int pageSize, String sortBy) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return this.reglementRepository.findAll(pageable);
	}

	@Override
	public Optional<Reglement> findOne(Long id) {
		// TODO Auto-generated method stub
		return this.reglementRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		this.reglementRepository.deleteById(id);
	}

}
