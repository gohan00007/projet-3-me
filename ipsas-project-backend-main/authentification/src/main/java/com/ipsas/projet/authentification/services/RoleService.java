package com.ipsas.projet.authentification.services;

import java.util.Optional;
import org.springframework.data.domain.Page;
import com.ipsas.projet.authentification.entities.Role;

/**
 * Service Interface for managing {@link Role}.
 */
public interface RoleService {
    /**
     * Save a role.
     *
     * @param role the entity to save.
     * @return the persisted entity.
     */
    Role save(Role role);

    /**
     * Updates a role.
     *
     * @param role the entity to update.
     * @return the persisted entity.
     */
    Role update(Long id, Role role);


    /**
     * Get all the roles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Role> findAll(int pageNo, int pageSize, String sortBy);

    /**
     * Get the "id" role.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Role> findOne(Long id);

    /**
     * Delete the "id" role.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
