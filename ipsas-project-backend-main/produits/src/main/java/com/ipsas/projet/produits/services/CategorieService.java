package com.ipsas.projet.produits.services;

import com.ipsas.projet.produits.entities.Categorie;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CategorieService {
    Categorie save(Categorie categorie);
    Categorie update(Long id, Categorie categorie);
    Page<Categorie> findAll(int pageNo, int pageSize, String sortBy);
    Optional<Categorie> findOne(Long id);
    void delete(Long id);
}
