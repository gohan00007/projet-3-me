package com.ipsas.projet.factures.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date date;
    private String reference, note, devise;
    private float prixHT, prixTVA, prixTTC, reste;

    @OneToMany(cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "facture")
    @JsonIgnoreProperties({"facture"})
    private Collection<LigneFacture> lignes;
    
    @Transient
    private Client client;
    private Long clientId;
}
