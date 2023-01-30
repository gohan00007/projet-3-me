package com.ipsas.projet.factures.services;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import com.ipsas.projet.factures.entities.Facture;

/**
 * Service Interface for managing {@link Facture}.
 */
public interface FactureService {
    /**
     * Save a facture.
     *
     * @param facture the entity to save.
     * @return the persisted entity.
     */
    Facture save(Facture facture);

    /**
     * Updates a facture.
     *
     * @param facture the entity to update.
     * @return the persisted entity.
     */
    Facture update(Long id, Facture facture);

    /**
     * Get all the factures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Facture> findAll(int pageNo, int pageSize, String sortBy);

    /**
     * Get the "id" facture.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Facture> findOne(Long id);

    /**
     * Delete the "id" facture.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    
    float clientChiffreAffaireGlobale(long clientId);
    float clientChiffreAffaireByAnnee(long clientId, int annee);
    float clientReste(long clientId);
    List<Facture> clientPayee(long clientId );
    List<Facture> clientNonPayee(long clientId );
    
    
    List<Facture> allPayee();
    List<Facture> allNonPayee();
    
    Facture updateReste(Long id, float montant ) ;
}
