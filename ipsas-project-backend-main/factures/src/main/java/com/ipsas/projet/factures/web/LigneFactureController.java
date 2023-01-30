package com.ipsas.projet.factures.web;

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
import com.ipsas.projet.factures.entities.LigneFacture;
import com.ipsas.projet.factures.services.LigneFactureService;
import com.ipsas.projet.factures.utils.AppConstants;

@RestController
@RequestMapping("/api/ligne-factures")
public class LigneFactureController {

    @Autowired
    LigneFactureService ligneFactureService;

    @PostMapping
    public LigneFacture save(@RequestBody LigneFacture ligneFacture) {
        return this.ligneFactureService.save(ligneFacture);
    }

    @PutMapping("/{id}")
    public LigneFacture update(@PathVariable("id") Long id, @RequestBody LigneFacture ligneFacture) {
        return this.ligneFactureService.update(id, ligneFacture);
    }

    @GetMapping
    public Page<LigneFacture> findAll(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy) {

        return this.ligneFactureService.findAll(pageNo, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    public Optional<LigneFacture> findOne(@PathVariable("id") Long id) {
        // TODO Auto-generated method stub
        return this.ligneFactureService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.ligneFactureService.delete(id);
    }

}
