package com.ipsas.projet.produits.services.impl;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.ipsas.projet.produits.entities.Taxe;
import com.ipsas.projet.produits.repositories.TaxeRepository;
import com.ipsas.projet.produits.services.TaxeService;

@Service
@Transactional
public class TaxeServiceImpl implements TaxeService {

    @Autowired
    TaxeRepository taxeRepository;

    @Override
    public Taxe save(Taxe taxe) {
        // TODO Auto-generated method stub
        return this.taxeRepository.save(taxe);
    }

    @Override
    public Taxe update(Long id, Taxe taxe) {
        // TODO Auto-generated method stub
        taxe.setId(id);
        return this.taxeRepository.save(taxe);
    }

    @Override
    public Page<Taxe> findAll(int pageNo, int pageSize, String sortBy) {
        // TODO Auto-generated method stub
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return this.taxeRepository.findAll(pageable);
    }

    @Override
    public Optional<Taxe> findOne(Long id) {
        // TODO Auto-generated method stub
        return this.taxeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        this.taxeRepository.deleteById(id);
    }

}
