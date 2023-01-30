package com.ipsas.projet.clients.services;

import java.util.Optional;
import org.springframework.data.domain.Page;
import com.ipsas.projet.clients.entities.Client;

/**
 * Service Interface for managing {@link Client}.
 */
public interface ClientService {
    /**
     * Save a client.
     *
     * @param client the entity to save.
     * @return the persisted entity.
     */
    Client save(Client client);

    /**
     * Updates a client.
     *
     * @param client the entity to update.
     * @return the persisted entity.
     */
    Client update(Long id, Client client);


    /**
     * Get all the clients.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Client> findAll(int pageNo, int pageSize, String sortBy);

    /**
     * Get the "id" client.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Client> findOne(Long id);

    /**
     * Delete the "id" client.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
