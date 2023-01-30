package com.ipsas.projet.reglements.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ipsas.projet.reglements.entities.Reglement;

@Repository
public interface ReglementRepository extends JpaRepository<Reglement, Long> {

}
