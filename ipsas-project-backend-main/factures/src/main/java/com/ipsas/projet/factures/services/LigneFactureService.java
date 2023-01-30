package com.ipsas.projet.factures.services;

import java.util.Optional;
import org.springframework.data.domain.Page;

import com.ipsas.projet.factures.entities.LigneFacture;

/**
 * Service Interface for managing {@link LigneFacture}.
 */
public interface LigneFactureService {
    /**
     * Save a ligneFacture.
     *
     * @param ligneFacture the entity to save.
     * @return the persisted entity.
     */
    LigneFacture save(LigneFacture ligneFacture);

    /**
     * Updates a ligneFacture.
     *
     * @param ligneFacture the entity to update.
     * @return the persisted entity.
     */
    LigneFacture update(Long id, LigneFacture ligneFacture);

    /**
     * Get all the ligneFactures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LigneFacture> findAll(int pageNo, int pageSize, String sortBy);

    /**
     * Get the "id" ligneFacture.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LigneFacture> findOne(Long id);

    /**
     * Delete the "id" ligneFacture.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
