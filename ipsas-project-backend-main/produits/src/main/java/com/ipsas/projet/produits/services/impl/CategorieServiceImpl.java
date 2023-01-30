package com.ipsas.projet.produits.services.impl;

import com.ipsas.projet.produits.entities.Categorie;
import com.ipsas.projet.produits.repositories.CategorieRepository;
import com.ipsas.projet.produits.services.CategorieService;
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
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;
    
    @Override
    public Categorie save(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie update(Long id, Categorie categorie) {
        categorie.setId(id);
        return categorieRepository.save(categorie);
    }

    @Override
    public Page<Categorie> findAll(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return categorieRepository.findAll(pageable);

    }

    @Override
    public Optional<Categorie> findOne(Long id) {
        return categorieRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        categorieRepository.deleteById(id);
    }
}
