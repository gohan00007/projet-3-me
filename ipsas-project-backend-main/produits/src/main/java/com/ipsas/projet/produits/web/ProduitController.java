package com.ipsas.projet.produits.web;

import java.util.List;
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
import com.ipsas.projet.produits.entities.Produit;
import com.ipsas.projet.produits.services.ProduitService;
import com.ipsas.projet.produits.utils.AppConstants;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    @Autowired
    ProduitService produitService;

    @PostMapping
    public Produit save(@RequestBody Produit produit) {
        return this.produitService.save(produit);
    }

    @PutMapping("/{id}")
    public Produit update(@PathVariable("id") Long id, @RequestBody Produit produit) {
        return this.produitService.update(id, produit);
    }

    @GetMapping
    public Page<Produit> findAll(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy) {

        return this.produitService.findAll(pageNo, pageSize, sortBy);
    }
    
    @GetMapping("/repture")
    public List<Produit> findRepture() {

        return this.produitService.getAllZeroStock();
    }

    @GetMapping("/{id}")
    public Optional<Produit> findOne(@PathVariable("id") Long id) {
        // TODO Auto-generated method stub
        return this.produitService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.produitService.delete(id);
    }

}
