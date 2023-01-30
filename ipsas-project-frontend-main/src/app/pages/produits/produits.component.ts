import { Component } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzTableQueryParams } from 'ng-zorro-antd/table';
import { Categorie } from 'src/app/models/categorie.model';
import { Marque } from 'src/app/models/marque.model';
import { Produit } from 'src/app/models/produit.model';
import { Taxe } from 'src/app/models/taxe.model';
import { CategorieService } from 'src/app/services/categorie.service';
import { MarqueService } from 'src/app/services/marque.service';
import { ProduitService } from 'src/app/services/produit.service';
import { TaxeService } from 'src/app/services/taxe.service';

@Component({
  selector: 'app-produits',
  templateUrl: './produits.component.html',
  styleUrls: ['./produits.component.scss']
})
export class ProduitsComponent {

  // local variables
  current: Produit = new Produit(0, '', '', 0, 0, 0, 0);
  listOfData: Produit[] = [];

  taxes: Taxe[] = [];
  categories: Categorie[] = [];
  marques: Marque[] = [];

  isVisible = false;
  isConfirmLoading = false;
  isLoading = true;
  editCache: { [key: string]: { edit: boolean; data: Produit } } = {};
  total = 1;
  pageSize = 10;
  pageIndex = 1;

  /**
   * default constructor of current component
   *
   * @param message
   * @param service
   * @param marqueService
   * @param categorieService
   * @param taxeService
   */
  constructor(private message: NzMessageService, private service: ProduitService, private marqueService: MarqueService, private categorieService: CategorieService, private taxeService: TaxeService) {
    this.getData();
  }

  /**
   * get marques, categories and taxes from database
   */
  getData() {
    this.marqueService.getAllMarques(1, 1000).subscribe(
      (response: any) => {
        this.marques = response.content;
      }
    );

    this.categorieService.getAllCategories(1, 1000).subscribe(
      (response: any) => {
        this.categories = response.content;
      }
    );

    this.taxeService.getAllTaxes(1, 1000).subscribe(
      (response: any) => {
        this.taxes = response.content;
      }
    );
  }

  /**
   * when page index or page size change for the table pagination
   *
   * @param params
   */
  onQueryParamsChange(params: NzTableQueryParams): void {
    const { pageSize, pageIndex } = params;
    this.pageIndex = pageIndex;
    this.pageSize = pageSize;
    this.getProduits(pageIndex, pageSize);
  }

  /**
   * show the editable fields
   *
   * @param id
   */
  startEdit(id: number): void {
    this.editCache[id].edit = true;
    console.log(this.editCache[id]);
  }

  /**
   * hide the editable fields
   *
   * @param id
   */
  cancelEdit(id: number): void {
    const index = this.listOfData.findIndex(item => item.id === id);
    this.editCache[id] = {
      data: { ...this.listOfData[index] },
      edit: false
    };
  }

  /**
   * save updates
   *
   * @param id
   */
  saveEdit(id: number): void {
    const index = this.listOfData.findIndex(item => item.id === id);

    console.log(this.editCache[id].data);
    // editCache[data.id].data
    Object.assign(this.listOfData[index], this.editCache[id].data);
    this.editCache[id].edit = false;

    this.service.updateExistingProduit(this.editCache[id].data)
      .subscribe(
        () => {
          this.message.loading(`produit with id ${id} updated :) actualisation des données `);
          this.getProduits();
        },
        (error: any) => {
          console.log(error);
          this.message.error(error.message);
          this.isLoading = false;
        }
      )
      ;
  }

  /**
   * update the editable list
   */
  updateEditCache(): void {
    this.listOfData?.forEach(item => {
      this.editCache[item.id] = {
        edit: false,
        data: { ...item }
      };
    });
  }

  /**
   * confirm delete produit by id
   *
   * @param id
   */
  deleteProduit(id: number) {

    this.service.deleteSingleProduit(id)
      .subscribe(
        (response: any) => {
          this.message.loading(`produit ${id} supprimée :) actualisation des données ... `);
          this.getProduits();
        },
        (error: any) => {
          console.log(error);
          this.message.error(error.message);
          this.isLoading = false;
        }
      )
      ;
  }

  /**
   * show modal for creating new produit
   */
  showCreateModal() {
    this.current = new Produit(0, '', '', 0, 0, 0, 0);
    this.isVisible = true;

  }

  /**
   * handle submit action of creating new produit
   */
  handleOk(): void {
    this.isConfirmLoading = true;

    this.service.createNewProduit(this.current).subscribe(
      () => {
        this.isConfirmLoading = false;
        this.isVisible = false;
        this.current = new Produit(0, '', '', 0, 0, 0, 0);
        this.message.loading(`nouveau produit  ajoutée :) actualisation des données ... `);
        this.getProduits();
      }, (error: any) => {
        this.message.error(error.message);
        this.isConfirmLoading = false;
      }
    )
  }

  /**
   * handle cancel action of modal
  */
  handleCancel(): void {
    this.isVisible = false;
  }

  /**
   * get produits from database with paginable options
   */
  getProduits(pageIndex = 1, pageSize = 10, sort = 'id') {
    this.isLoading = true;
    this.service.getAllProduits(pageIndex, pageSize, sort)
      .subscribe(
        (response: any) => {
          this.listOfData = response.content;
          this.total = response.totalElements;
          this.updateEditCache();
          this.isLoading = false;
        },
        (error: any) => {
          this.message.error(error);
          this.isLoading = false;
        }
      )
      ;
  }


  /**
   * change the modal status
   */
  modalVisibility() {
    this.isVisible = !this.isVisible;
  }

}

