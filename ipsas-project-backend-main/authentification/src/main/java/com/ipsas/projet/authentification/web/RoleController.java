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
import com.ipsas.projet.authentification.entities.Role;
import com.ipsas.projet.authentification.services.RoleService;
import com.ipsas.projet.authentification.utils.AppConstants;

@RestController
@RequestMapping("/api/roles")
public class RoleController{

	@Autowired
	RoleService roleService;

	@PostMapping
	public Role save( @RequestBody Role role) {
		return this.roleService.save(role);
	}

	@PutMapping("/{id}")
	public Role update( @PathVariable("id") Long id,  @RequestBody Role role) {
		return this.roleService.update(id, role);
	}

	@GetMapping
	public Page<Role> findAll( 
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy) {
		
		return this.roleService.findAll(pageNo, pageSize, sortBy);
	}

	@GetMapping("/{id}")
	public Optional<Role> findOne(@PathVariable("id") Long id) {
		// TODO Auto-generated method stub
		return this.roleService.findOne(id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		this.roleService.delete(id);
	}
	
	
}
