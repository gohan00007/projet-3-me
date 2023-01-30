import { Component } from '@angular/core';
import { Client } from 'src/app/models/client.model';
import { Facture } from 'src/app/models/facture.model';
import { Produit } from 'src/app/models/produit.model';
import { ClientService } from 'src/app/services/client.service';
import { FactureService } from 'src/app/services/facture.service';
import { ProduitService } from 'src/app/services/produit.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

  public produits: Produit[] = [];
  public clients: Client[] = [];
  clientId: number = 0;
  annee: number = 0;

  clientChiffreGlobale: number = 0;
  clientChiffreAnnee: number = 0;
  clientReste: number = 0;
  clientListePayee: Facture[] = [];
  clientListeNonPayee: Facture[] = [];

  listePayee: Facture[] = [];
  listeNonPayee: Facture[] = [];


  constructor(private produitService: ProduitService, private factureService: FactureService, private clientService: ClientService) {
    this.getData();
  }

  /**
   * get All needed data
   */
  getData() {
    this.produitService.getReptureProduit().subscribe(
      (response: any) => {
        this.produits = response;
      }
    );

    this.clientService.getAllClients(1, 1000).subscribe(
      (response: any) => {
        this.clients = response.content;
      }
    );

    this.factureService.getListePayee().subscribe(
      (response: any) => {
        this.listePayee = response;
      }
    );

    this.factureService.getListeNonPayee().subscribe(
      (response: any) => {
        this.listeNonPayee = response;
      }
    );
  }

  refreshClientData() {

    if (this.clientId > 0) {
      this.factureService.getChiffreAffaireClientGlobale(this.clientId).subscribe(
        (response: any) => {
          this.clientChiffreGlobale = response;
        }
      );

      this.factureService.getClientReste(this.clientId).subscribe(
        (response: any) => {
          this.clientReste = response;
        }
      );


      this.factureService.getClientListePayee(this.clientId).subscribe(
        (response: any) => {
          this.clientListePayee = response;
        }
      );


      this.factureService.getClientListeNonPayee(this.clientId).subscribe(
        (response: any) => {
          this.clientListeNonPayee = response;
        }
      );


      // check if year selected
      if (this.annee > 0) {
        this.factureService.getChiffreAffaireClientAnnee(this.clientId, this.annee).subscribe(
          (response: any) => {
            this.clientChiffreAnnee = response;
          }
        );
      }

    }

  }

  /**
   * when year has changed
   *
   * @param annee
   */
  anneeChange(annee: number) {

    this.annee = new Date(annee).getFullYear();

    console.log(this.annee);
    this.refreshClientData();
  }

  /**
   * when client has changed
   *
   * @param clientId
   */
  clientChange(clientId: number) {
    this.clientId = clientId;

    this.refreshClientData();
  }
}
