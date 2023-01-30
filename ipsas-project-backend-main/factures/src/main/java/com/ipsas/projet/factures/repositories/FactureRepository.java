package com.ipsas.projet.factures.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ipsas.projet.factures.entities.Facture;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {

	@Query("SELECT SUM(f.reste) FROM Facture f")
	Float getUnpaidTotal();
	
	@Query("SELECT SUM(f.reste) FROM Facture f WHERE f.clientId = ?1 AND f.reste > 0")
	Float getClientUnpaid(long clientId);
	
	@Query("SELECT SUM(f.reste) FROM Facture f WHERE f.clientId = ?1 AND f.reste = 0")
	Float getClientPaid(long clientId);
	
	@Query("SELECT SUM(f.prixTTC) FROM Facture f")
	Float getAllAffaires();
	
	@Query("SELECT SUM(f.prixTTC) FROM Facture f where YEAR(f.date) = ?1")
	Float getAllAffairesByYear();
	
	
	@Query("SELECT f FROM Facture f where f.reste = 0 AND f.clientId = ?1")
	List<Facture> getAllClientPaid(long clientId);
	
	@Query("SELECT f FROM Facture f where f.reste > 0")
	List<Facture> getAllClientUnpaid(long clientId);
	
	@Query("SELECT SUM(f.prixTTC) FROM Facture f where f.clientId = ?1")
	Float getAllAffairesByClient(long clientId);
	
	@Query("SELECT SUM(f.prixTTC) FROM Facture f where f.clientId = ?1 and  YEAR(f.date) = ?2")
	Float getAllAffairesByClientAndYear(long clientId, int year);
	
	@Query("SELECT f FROM Facture f where f.reste = 0")
	List<Facture> getAllPaid();
	
	@Query("SELECT f FROM Facture f where f.reste > 0")
	List<Facture> getAllUnpaid();
}
