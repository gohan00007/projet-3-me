package com.ipsas.projet.produits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ipsas.projet.produits.entities.Taxe;

@Repository
public interface TaxeRepository extends JpaRepository<Taxe, Long> {

}
