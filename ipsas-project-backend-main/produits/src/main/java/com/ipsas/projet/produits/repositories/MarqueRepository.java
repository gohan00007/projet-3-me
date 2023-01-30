package com.ipsas.projet.produits.repositories;

import com.ipsas.projet.produits.entities.Marque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarqueRepository extends JpaRepository<Marque, Long> {
}
