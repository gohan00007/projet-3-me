import { Component } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzTableQueryParams } from 'ng-zorro-antd/table';
import { Taxe } from 'src/app/models/taxe.model';
import { TaxeService } from 'src/app/services/taxe.service';

@Component({
  selector: 'app-taxes',
  templateUrl: './taxes.component.html',
  styleUrls: ['./taxes.component.scss']
})
export class TaxesComponent {

  // local variables
  current: Taxe = new Taxe(0, '', 0, 'POURCENTAGE');
  listOfData: Taxe[] = [];

  isVisible = false;
  isConfirmLoading = false;
  isLoading = true;
  editCache: { [key: string]: { edit: boolean; data: Taxe } } = {};
  total = 1;
  pageSize = 10;
  pageIndex = 1;

  /**
   * default constructor of current component
   *
   * @param service
   */
  constructor(private message: NzMessageService, private service: TaxeService) {
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
    this.getTaxes(pageIndex, pageSize);
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

    this.service.updateExistingTaxe(this.editCache[id].data)
      .subscribe(
        () => {
          this.message.loading(`taxe with id ${id} updated :) actualisation des données `);
          this.getTaxes();
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
   * confirm delete taxe by id
   *
   * @param id
   */
  deleteTaxe(id: number) {

    this.service.deleteSingleTaxe(id)
      .subscribe(
        (response: any) => {
          this.message.loading(`taxe ${id} supprimée :) actualisation des données ... `);
          this.getTaxes();
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
   * show modal for creating new taxe
   */
  showCreateModal() {
    this.current = new Taxe(0, '', 0, 'POURCENTAGE');
    this.isVisible = true;

  }

  /**
   * handle submit action of creating new taxe
   */
  handleOk(): void {
    this.isConfirmLoading = true;

    this.service.createNewTaxe(this.current).subscribe(
      () => {
        this.isConfirmLoading = false;
        this.isVisible = false;
        this.current = new Taxe(0, '', 0, 'POURCENTAGE');
        this.message.loading(`nouveau taxe  ajoutée :) actualisation des données ... `);
        this.getTaxes();
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
   * get taxes from database with paginable options
   */
  getTaxes(pageIndex = 1, pageSize = 10, sort = 'id') {
    this.isLoading = true;
    this.service.getAllTaxes(pageIndex, pageSize, sort)
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
 * change the taxe type
 *
 * @param id
 * @param genre
 */
  typeChange(id: number, type: string) {
    this.editCache[id].data.type = type;
  }
}

