package com.ipsas.projet.reglements.services;

import java.util.Optional;
import org.springframework.data.domain.Page;
import com.ipsas.projet.reglements.entities.Reglement;

/**
 * Service Interface for managing {@link Reglement}.
 */
public interface ReglementService {
    /**
     * Save a reglement.
     *
     * @param reglement the entity to save.
     * @return the persisted entity.
     */
    Reglement save(Reglement reglement);

    /**
     * Updates a reglement.
     *
     * @param reglement the entity to update.
     * @return the persisted entity.
     */
    Reglement update(Long id, Reglement reglement);


    /**
     * Get all the reglements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Reglement> findAll(int pageNo, int pageSize, String sortBy);

    /**
     * Get the "id" reglement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Reglement> findOne(Long id);

    /**
     * Delete the "id" reglement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
