package com.ipsas.projet.produits.services;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import com.ipsas.projet.produits.entities.Produit;

/**
 * Service Interface for managing {@link Produit}.
 */
public interface ProduitService {
    /**
     * Save a produit.
     *
     * @param produit the entity to save.
     * @return the persisted entity.
     */
    Produit save(Produit produit);

    /**
     * Updates a produit.
     *
     * @param produit the entity to update.
     * @return the persisted entity.
     */
    Produit update(Long id, Produit produit);

    /**
     * Get all the produits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Produit> findAll(int pageNo, int pageSize, String sortBy);

    /**
     * Get the "id" produit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Produit> findOne(Long id);

    /**
     * Delete the "id" produit.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    
    /**
     * Get All products with 0 quantity
     * @return
     */
    List<Produit> getAllZeroStock();
}
