package com.ipsas.projet.factures.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ipsas.projet.factures.entities.LigneFacture;

@Repository
public interface LigneFactureRepository extends JpaRepository<LigneFacture, Long> {

}
