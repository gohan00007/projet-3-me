package com.ipsas.projet.authentification.services.impl;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.ipsas.projet.authentification.entities.Role;
import com.ipsas.projet.authentification.repositories.RoleRepository;
import com.ipsas.projet.authentification.services.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role save(Role role) {
		// TODO Auto-generated method stub
		return this.roleRepository.save(role);
	}

	@Override
	public Role update(Long id, Role role) {
		// TODO Auto-generated method stub
		role.setId(id);
		return this.roleRepository.save(role);
	}

	@Override
	public Page<Role> findAll(int pageNo, int pageSize, String sortBy) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return this.roleRepository.findAll(pageable);
	}

	@Override
	public Optional<Role> findOne(Long id) {
		// TODO Auto-generated method stub
		return this.roleRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		this.roleRepository.deleteById(id);
	}

}
