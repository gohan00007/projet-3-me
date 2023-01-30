package com.ipsas.projet.factures.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Produit {

    private Long id;
    private String nom, designation, reference;
    private Integer quantite;
    private float prixHT, prixTVA, prixTTC;

}
