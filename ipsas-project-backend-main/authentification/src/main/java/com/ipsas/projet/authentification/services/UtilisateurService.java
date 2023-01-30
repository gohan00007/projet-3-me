package com.ipsas.projet.authentification.services;

import java.util.Optional;
import org.springframework.data.domain.Page;
import com.ipsas.projet.authentification.entities.Utilisateur;

/**
 * Service Interface for managing {@link Utilisateur}.
 */
public interface UtilisateurService {
    /**
     * Save a utilisateur.
     *
     * @param utilisateur the entity to save.
     * @return the persisted entity.
     */
    Utilisateur save(Utilisateur utilisateur);

    /**
     * Updates a utilisateur.
     *
     * @param utilisateur the entity to update.
     * @return the persisted entity.
     */
    Utilisateur update(Long id, Utilisateur utilisateur);

    /**
     * Get a utilisateur by email.
     *
     * @param utilisateur email.
     * @return the searched entity.
     */
    Utilisateur getUserByEmail(String email);
    
    /**
     * Get all the utilisateurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Utilisateur> findAll(int pageNo, int pageSize, String sortBy);

    /**
     * Get the "id" utilisateur.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Utilisateur> findOne(Long id);

    /**
     * Delete the "id" utilisateur.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
