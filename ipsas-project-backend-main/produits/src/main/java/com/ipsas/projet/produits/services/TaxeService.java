package com.ipsas.projet.produits.services;

import java.util.Optional;
import org.springframework.data.domain.Page;

import com.ipsas.projet.produits.entities.Taxe;

/**
 * Service Interface for managing {@link Taxe}.
 */
public interface TaxeService {
    /**
     * Save a taxe.
     *
     * @param taxe the entity to save.
     * @return the persisted entity.
     */
    Taxe save(Taxe taxe);

    /**
     * Updates a taxe.
     *
     * @param taxe the entity to update.
     * @return the persisted entity.
     */
    Taxe update(Long id, Taxe taxe);

    /**
     * Get all the taxes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Taxe> findAll(int pageNo, int pageSize, String sortBy);

    /**
     * Get the "id" taxe.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Taxe> findOne(Long id);

    /**
     * Delete the "id" taxe.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
