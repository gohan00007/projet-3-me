package com.ipsas.projet.clients.web;

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
import com.ipsas.projet.clients.entities.Client;
import com.ipsas.projet.clients.services.ClientService;
import com.ipsas.projet.clients.utils.AppConstants;

@RestController
@RequestMapping("/api/clients")
public class ClientController{

	@Autowired
	ClientService clientService;

	@PostMapping
	public Client save( @RequestBody Client client) {
		return this.clientService.save(client);
	}

	@PutMapping("/{id}")
	public Client update( @PathVariable("id") Long id,  @RequestBody Client client) {
		return this.clientService.update(id, client);
	}

	@GetMapping
	public Page<Client> findAll( 
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy) {
		
		return this.clientService.findAll(pageNo, pageSize, sortBy);
	}

	@GetMapping("/{id}")
	public Optional<Client> findOne(@PathVariable("id") Long id) {
		// TODO Auto-generated method stub
		return this.clientService.findOne(id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		this.clientService.delete(id);
	}
	
	
}
