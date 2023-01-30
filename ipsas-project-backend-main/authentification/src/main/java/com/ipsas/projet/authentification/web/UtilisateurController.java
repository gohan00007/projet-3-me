package com.ipsas.projet.authentification.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ipsas.projet.authentification.entities.Utilisateur;
import com.ipsas.projet.authentification.services.UtilisateurService;
import com.ipsas.projet.authentification.utils.AppConstants;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController{

	@Autowired
	UtilisateurService utilisateurService;

	@PostMapping
	public Utilisateur save( @RequestBody Utilisateur utilisateur) {
		return this.utilisateurService.save(utilisateur);
	}

	@PutMapping("/{id}")
	public Utilisateur update( @PathVariable("id") Long id,  @RequestBody Utilisateur utilisateur) {
		return this.utilisateurService.update(id, utilisateur);
	}

	@GetMapping
	public Page<Utilisateur> findAll( 
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy) {
		
		return this.utilisateurService.findAll(pageNo, pageSize, sortBy);
	}

	@GetMapping("/{id}")
	public Optional<Utilisateur> findOne(@PathVariable("id") Long id) {
		// TODO Auto-generated method stub
		return this.utilisateurService.findOne(id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		this.utilisateurService.delete(id);
	}
	
	
}
