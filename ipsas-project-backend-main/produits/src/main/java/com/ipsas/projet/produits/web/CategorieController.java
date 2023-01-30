package com.ipsas.projet.produits.web;

import com.ipsas.projet.produits.entities.Categorie;
import com.ipsas.projet.produits.services.CategorieService;
import com.ipsas.projet.produits.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    @Autowired
     CategorieService categorieService;

    @PostMapping
    public Categorie save(@RequestBody Categorie categorie) {
        return this.categorieService.save(categorie);
    }

    @PutMapping("/{id}")
    public Categorie update(@PathVariable("id") Long id, @RequestBody Categorie categorie) {
        return this.categorieService.update(id, categorie);
    }

    @GetMapping
    public Page<Categorie> findAll(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy) {

        return this.categorieService.findAll(pageNo, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    public Optional<Categorie> findOne(@PathVariable("id") Long id) {
        // TODO Auto-generated method stub
        return this.categorieService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.categorieService.delete(id);
    }


}
