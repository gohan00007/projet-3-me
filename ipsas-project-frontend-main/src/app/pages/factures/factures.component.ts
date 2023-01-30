import { Component } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzTableQueryParams } from 'ng-zorro-antd/table';
import { Client } from 'src/app/models/client.model';
import { Facture } from 'src/app/models/facture.model';
import { ClientService } from 'src/app/services/client.service';
import { FactureService } from 'src/app/services/facture.service';

@Component({
  selector: 'app-factures',
  templateUrl: './factures.component.html',
  styleUrls: ['./factures.component.scss']
})
export class FacturesComponent {

  // local variables
  current: Facture = new Facture(0, '', '', '', new Date(), 0, 0, 0);
  listOfData: Facture[] = [];

  clients: Client[] = [];


  isVisible = false;
  isConfirmLoading = false;
  isLoading = true;
  editCache: { [key: string]: { edit: boolean; data: Facture } } = {};
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
  constructor(private message: NzMessageService, private service: FactureService, private clientService: ClientService) {
    this.getData();
  }

  /**
   * get marques, categories and taxes from database
   */
  getData() {
    this.clientService.getAllClients(1, 1000).subscribe(
      (response: any) => {
        this.clients = response.content;
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
    this.getFactures(pageIndex, pageSize);
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

    this.service.updateExistingFacture(this.editCache[id].data)
      .subscribe(
        () => {
          this.message.loading(`facture with id ${id} updated :) actualisation des données `);
          this.getFactures();
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
   * confirm delete facture by id
   *
   * @param id
   */
  deleteFacture(id: number) {

    this.service.deleteSingleFacture(id)
      .subscribe(
        (response: any) => {
          this.message.loading(`facture ${id} supprimée :) actualisation des données ... `);
          this.getFactures();
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
   * show modal for creating new facture
   */
  showCreateModal() {
    this.current = new Facture(0, '', '', '', new Date(), 0, 0, 0);
    this.isVisible = true;

  }

  /**
   * handle submit action of creating new facture
   */
  handleOk(): void {
    this.isConfirmLoading = true;

    this.service.createNewFacture(this.current).subscribe(
      () => {
        this.isConfirmLoading = false;
        this.isVisible = false;
        this.current = new Facture(0, '', '', '', new Date(), 0, 0, 0);
        this.message.loading(`nouveau facture  ajoutée :) actualisation des données ... `);
        this.getFactures();
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
   * get factures from database with paginable options
   */
  getFactures(pageIndex = 1, pageSize = 10, sort = 'id') {
    this.isLoading = true;
    this.service.getAllFactures(pageIndex, pageSize, sort)
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

  /**
* change the facture client
*
* @param id
* @param genre
*/
  clientChange(id: number, clientId: number) {
    this.editCache[id].data.clientId = clientId;
  }

}

