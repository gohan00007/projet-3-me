package com.ipsas.projet.reglements.entities;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Facture {

    private Long id;
    
    private Date date;
    private String reference, note, devise;
    private float prixHT, prixTVA, prixTTC, reste;
    private Long clientId;
}
