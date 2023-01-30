package com.ipsas.projet.authentification.services.impl;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.ipsas.projet.authentification.entities.Utilisateur;
import com.ipsas.projet.authentification.repositories.UtilisateurRepository;
import com.ipsas.projet.authentification.services.UtilisateurService;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {
	
	@Autowired
	UtilisateurRepository userRepository;

	@Override
	public Utilisateur save(Utilisateur user) {
		// TODO Auto-generated method stub
		return this.userRepository.save(user);
	}

	@Override
	public Utilisateur update(Long id, Utilisateur user) {
		// TODO Auto-generated method stub
		user.setId(id);
		return this.userRepository.save(user);
	}

	@Override
	public Page<Utilisateur> findAll(int pageNo, int pageSize, String sortBy) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return this.userRepository.findAll(pageable);
	}

	@Override
	public Optional<Utilisateur> findOne(Long id) {
		// TODO Auto-generated method stub
		return this.userRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		this.userRepository.deleteById(id);
	}

	@Override
	public Utilisateur getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return this.userRepository.findByEmail(email);
	}

}
