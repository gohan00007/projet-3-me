package com.ipsas.projet.clients.services.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.ipsas.projet.clients.entities.Client;
import com.ipsas.projet.clients.repositories.ClientRepository;
import com.ipsas.projet.clients.services.ClientService;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	ClientRepository clientRepository;

	@Override
	public Client save(Client client) {
		// TODO Auto-generated method stub
		return this.clientRepository.save(client);
	}

	@Override
	public Client update(Long id, Client client) {
		// TODO Auto-generated method stub
		client.setId(id);
		return this.clientRepository.save(client);
	}

	@Override
	public Page<Client> findAll(int pageNo, int pageSize, String sortBy) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return this.clientRepository.findAll(pageable);
	}

	@Override
	public Optional<Client> findOne(Long id) {
		// TODO Auto-generated method stub
		return this.clientRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		this.clientRepository.deleteById(id);
	}

}
