package com.ipsas.projet.authentification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.ipsas.projet.authentification.entities.Role;

@RepositoryRestController
public interface RoleRepository extends JpaRepository<Role, Long> {

}
