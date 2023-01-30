package com.ipsas.projet.produits.services.impl;

import com.ipsas.projet.produits.entities.Marque;
import com.ipsas.projet.produits.repositories.MarqueRepository;
import com.ipsas.projet.produits.services.MarqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
@Transactional
public class MarqueServiceImpl implements MarqueService {

    @Autowired
    private MarqueRepository marqueRepository;
    
    @Override
    public Marque save(Marque marque) {
        return marqueRepository.save(marque);
    }

    @Override
    public Marque update(Long id, Marque marque) {
        marque.setId(id);
        return marqueRepository.save(marque);
    }

    @Override
    public Page<Marque> findAll(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return marqueRepository.findAll(pageable);
    }

    @Override
    public Optional<Marque> findOne(Long id) {
        return marqueRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        marqueRepository.deleteById(id);
    }
}
