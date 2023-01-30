package com.ipsas.projet.produits.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom, designation, reference;
    private Integer quantite;
    private float prixHT, prixTVA, prixTTC;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Taxe taxe;
    
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Marque marque;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Categorie categorie;
}
