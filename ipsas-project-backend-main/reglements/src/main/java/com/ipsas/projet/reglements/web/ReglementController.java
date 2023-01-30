package com.ipsas.projet.reglements.web;

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
import com.ipsas.projet.reglements.entities.Reglement;
import com.ipsas.projet.reglements.services.ReglementService;
import com.ipsas.projet.reglements.utils.AppConstants;

@RestController
@RequestMapping("/api/reglements")
public class ReglementController{

	@Autowired
	ReglementService reglementService;

	@PostMapping
	public Reglement save( @RequestBody Reglement reglement) {
		return this.reglementService.save(reglement);
	}

	@PutMapping("/{id}")
	public Reglement update( @PathVariable("id") Long id,  @RequestBody Reglement reglement) {
		return this.reglementService.update(id, reglement);
	}

	@GetMapping
	public Page<Reglement> findAll( 
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy) {
		
		return this.reglementService.findAll(pageNo, pageSize, sortBy);
	}

	@GetMapping("/{id}")
	public Optional<Reglement> findOne(@PathVariable("id") Long id) {
		// TODO Auto-generated method stub
		return this.reglementService.findOne(id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		this.reglementService.delete(id);
	}
	
	
}
