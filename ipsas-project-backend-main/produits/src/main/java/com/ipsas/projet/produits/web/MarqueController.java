package com.ipsas.projet.produits.web;

import com.ipsas.projet.produits.entities.Marque;
import com.ipsas.projet.produits.services.MarqueService;
import com.ipsas.projet.produits.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/marques")
public class MarqueController {

    @Autowired
    MarqueService marqueService;

    @PostMapping
    public Marque save(@RequestBody Marque marque) {
        return this.marqueService.save(marque);
    }

    @PutMapping("/{id}")
    public Marque update(@PathVariable("id") Long id, @RequestBody Marque marque) {
        return this.marqueService.update(id, marque);
    }

    @GetMapping
    public Page<Marque> findAll(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy) {

        return this.marqueService.findAll(pageNo, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    public Optional<Marque> findOne(@PathVariable("id") Long id) {
        // TODO Auto-generated method stub
        return this.marqueService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.marqueService.delete(id);
    }


}
