package com.ipsas.projet.factures.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client {

    private Long id;
    private String nom, prenom, telephone, adresse, genre;
    private Integer cin;

}
