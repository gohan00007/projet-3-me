package com.ipsas.projet.produits.services;

import com.ipsas.projet.produits.entities.Marque;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface MarqueService {



        Marque save(Marque marque);


        Marque update(Long id, Marque marque);

        Page<Marque> findAll(int pageNo, int pageSize, String sortBy);

        Optional<Marque> findOne(Long id);
        void delete(Long id);

}
