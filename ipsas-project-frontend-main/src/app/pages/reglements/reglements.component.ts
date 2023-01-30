import { Component } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzTableQueryParams } from 'ng-zorro-antd/table';
import { Facture } from 'src/app/models/facture.model';
import { Reglement } from 'src/app/models/reglement.model';
import { FactureService } from 'src/app/services/facture.service';
import { ReglementService } from 'src/app/services/reglement.service';

@Component({
  selector: 'app-reglements',
  templateUrl: './reglements.component.html',
  styleUrls: ['./reglements.component.scss']
})
export class ReglementsComponent {

  // local variables
  current: Reglement = new Reglement(0, '', '', new Date(), 0, 'VIREMENT');
  listOfData: Reglement[] = [];

  factures: Facture[] = [];


  isVisible = false;
  isConfirmLoading = false;
  isLoading = true;
  editCache: { [key: string]: { edit: boolean; data: Reglement } } = {};
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
  constructor(private message: NzMessageService, private service: ReglementService, private factureService: FactureService) {
    this.getData();
  }

  /**
   * get marques, categories and taxes from database
   */
  getData() {
    this.factureService.getAllFactures(1, 1000).subscribe(
      (response: any) => {
        this.factures = response.content;
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
    this.getReglements(pageIndex, pageSize);
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

    this.service.updateExistingReglement(this.editCache[id].data)
      .subscribe(
        () => {
          this.message.loading(`reglement with id ${id} updated :) actualisation des données `);
          this.getReglements();
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
   * confirm delete reglement by id
   *
   * @param id
   */
  deleteReglement(id: number) {

    this.service.deleteSingleReglement(id)
      .subscribe(
        (response: any) => {
          this.message.loading(`reglement ${id} supprimée :) actualisation des données ... `);
          this.getReglements();
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
   * show modal for creating new reglement
   */
  showCreateModal() {
    this.current = new Reglement(0, '', '', new Date(), 0, '');
    this.isVisible = true;

  }

  /**
   * handle submit action of creating new reglement
   */
  handleOk(): void {
    this.isConfirmLoading = true;

    this.service.createNewReglement(this.current).subscribe(
      () => {
        this.isConfirmLoading = false;
        this.isVisible = false;
        this.current = new Reglement(0, '', '', new Date(), 0, '');
        this.message.loading(`nouveau reglement  ajoutée :) actualisation des données ... `);
        this.getReglements();
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
   * get reglements from database with paginable options
   */
  getReglements(pageIndex = 1, pageSize = 10, sort = 'id') {
    this.isLoading = true;
    this.service.getAllReglements(pageIndex, pageSize, sort)
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
* change the reglement type
*
* @param id
* @param genre
*/
  typeChange(id: number, type: string) {
    this.editCache[id].data.type = type;
  }


  /**
  * change the current reglement type
  *
  * @param type
  */
  currentTypeChange(type: string) {
    this.current.type = type;
  }

  /**
* change the current reglement type
*
* @param type
*/
  currentFactureChange(factureId: number) {
    this.current.factureId = factureId;
  }
}

