package com.ipsas.projet.factures.web;

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
import com.ipsas.projet.factures.entities.Facture;
import com.ipsas.projet.factures.services.FactureService;
import com.ipsas.projet.factures.utils.AppConstants;

@RestController
@RequestMapping("/api/factures")
public class FactureController {

    @Autowired
    FactureService factureService;

    @PostMapping
    public Facture save(@RequestBody Facture facture) {
        return this.factureService.save(facture);
    }

    @PutMapping("/{id}")
    public Facture update(@PathVariable("id") Long id, @RequestBody Facture facture) {
    	System.out.println("********************");
    	System.out.println(facture);
        return this.factureService.update(id, facture);
    }
    
    @PutMapping("/{id}/montant/{montant}")
    public Facture update(@PathVariable("id") Long id, @PathVariable("montant") float montant) {
    	System.out.println("********************");
        return this.factureService.updateReste(id, montant);
    }

    @GetMapping
    public Page<Facture> findAll(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy) {

        return this.factureService.findAll(pageNo, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    public Optional<Facture> findOne(@PathVariable("id") Long id) {
        // TODO Auto-generated method stub
        return this.factureService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.factureService.delete(id);
    }
    
    @GetMapping("/clients/{id}/chiffre-affaire")
	public float clientChiffreAffaireGlobale(@PathVariable("id")  long clientId) {
		// TODO Auto-generated method stub
		return this.factureService.clientChiffreAffaireGlobale(clientId);
	}
    @GetMapping("/clients/{id}/chiffre-affaire-annee/{annee}")
	public float clientChiffreAffaireByAnnee(@PathVariable("id")  long clientId, @PathVariable("annee")  int annee) {
		// TODO Auto-generated method stub
		return this.factureService.clientChiffreAffaireByAnnee(clientId, annee);
	}

    @GetMapping("/clients/{id}/reste")
	public float clientReste(@PathVariable("id") long clientId) {
		// TODO Auto-generated method stub
		return this.factureService.clientReste(clientId);
	}

    @GetMapping("/clients/{id}/liste-payee")
	public List<Facture> clientPayee(@PathVariable("id") long clientId) {
		// TODO Auto-generated method stub
		return this.factureService.clientPayee(clientId);
	}

    @GetMapping("/clients/{id}/liste-non-payee")
	public List<Facture> clientNonPayee(@PathVariable("id") long clientId) {
		// TODO Auto-generated method stub
		return this.factureService.clientNonPayee(clientId);
	}

    @GetMapping("/payee")
	public List<Facture> allPayee() {
		// TODO Auto-generated method stub
		return this.factureService.allPayee();
	}

    @GetMapping("/non-payee")
	public List<Facture> allNonPayee() {
		// TODO Auto-generated method stub
		return this.factureService.allNonPayee();
	}

}
