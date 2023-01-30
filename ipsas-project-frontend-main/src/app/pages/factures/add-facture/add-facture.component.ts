import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Client } from 'src/app/models/client.model';
import { Facture } from 'src/app/models/facture.model';
import { LigneFacture } from 'src/app/models/ligne-facture.model';
import { Produit } from 'src/app/models/produit.model';
import { ClientService } from 'src/app/services/client.service';
import { FactureService } from 'src/app/services/facture.service';
import { ProduitService } from 'src/app/services/produit.service';

@Component({
  selector: 'app-add-facture',
  templateUrl: './add-facture.component.html',
  styleUrls: ['./add-facture.component.scss']
})
export class AddFactureComponent {
  facture: Facture = new Facture(0, '', '', '', new Date(), 0, 0, 0);
  clients: Client[] = [];
  produits: Produit[] = [];
  isVisible = false;
  isConfirmLoading = false;
  current: LigneFacture = new LigneFacture(0, 0, 0, 0, 0);

  /**
 * default constructor of current component
 *
 * @param service
 */
  constructor(private message: NzMessageService, private service: FactureService, private clientService: ClientService, private produitService: ProduitService, private router: Router) {
    this.getAllClients();
  }

  getAllClients() {
    this.clientService.getAllClients(1, 1000).subscribe(
      (response: any) => {
        this.clients = response.content;
      }
    );

    this.produitService.getAllProduits(1, 1000).subscribe(
      (response: any) => {
        this.produits = response.content;
      }
    );
  }

  /**
 * handle submit action of creating new client
 */
  handleOk(): void {
    this.facture.lignes.push(this.current);
    this.current.prixHT = this.current.produit.prixHT * this.current.quantite;
    this.current.prixTVA = this.current.produit.prixTVA * this.current.quantite;
    this.current.prixTTC = this.current.prixTVA + this.current.prixHT;

    this.facture.prixHT += this.current.prixHT;
    this.facture.prixTTC += this.current.prixTTC;
    this.facture.prixTVA += this.current.prixTVA;
    this.current = new LigneFacture(0, 0, 0, 0, 0);
    this.isVisible = false;
  }

  /**
   * handle cancel action of modal
  */
  handleCancel(): void {
    this.isVisible = false;
  }

  addFacture() {

    console.log(this.facture);

    this.isConfirmLoading = true;

    this.service.createNewFacture(this.facture).subscribe(
      () => {
        this.isConfirmLoading = false;
        this.isVisible = false;
        this.facture = new Facture(0, '', '', '', new Date(), 0, 0, 0);
        this.message.loading(`nouveau client  ajoutée :) actualisation des données ... `);
        this.router.navigate(['/factures']);
      }, (error: any) => {
        this.message.error(error.message);
        this.isConfirmLoading = false;
      }
    )
  }

  /**
 * change the modal status
 */
  modalVisibility() {
    this.isVisible = !this.isVisible;
  }

  /**
  * change the facture product
  *
  * @param product
  */
  produitChange(produit: Produit) {
    this.current.produit = produit;
    this.current.produitId = produit.id;
  }
}
