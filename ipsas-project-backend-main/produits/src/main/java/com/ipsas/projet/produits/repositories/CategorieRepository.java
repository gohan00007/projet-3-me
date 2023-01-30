package com.ipsas.projet.produits.repositories;

import com.ipsas.projet.produits.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie , Long> {

}
