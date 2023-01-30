package com.ipsas.projet.produits.web;

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
import com.ipsas.projet.produits.entities.Taxe;
import com.ipsas.projet.produits.services.TaxeService;
import com.ipsas.projet.produits.utils.AppConstants;

@RestController
@RequestMapping("/api/taxes")
public class TaxeController {

    @Autowired
    TaxeService taxeService;

    @PostMapping
    public Taxe save(@RequestBody Taxe taxe) {
        return this.taxeService.save(taxe);
    }

    @PutMapping("/{id}")
    public Taxe update(@PathVariable("id") Long id, @RequestBody Taxe taxe) {
        return this.taxeService.update(id, taxe);
    }

    @GetMapping
    public Page<Taxe> findAll(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy) {

        return this.taxeService.findAll(pageNo, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    public Optional<Taxe> findOne(@PathVariable("id") Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.taxeService.delete(id);
    }

}
