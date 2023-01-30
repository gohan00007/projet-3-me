import { Component } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzTableQueryParams } from 'ng-zorro-antd/table';
import { Marque } from 'src/app/models/marque.model';
import { MarqueService } from 'src/app/services/marque.service';

@Component({
  selector: 'app-marques',
  templateUrl: './marques.component.html',
  styleUrls: ['./marques.component.scss']
})
export class MarquesComponent {

  // local variables
  current: Marque = new Marque(0, '');
  listOfData: Marque[] = [];

  isVisible = false;
  isConfirmLoading = false;
  isLoading = true;
  editCache: { [key: string]: { edit: boolean; data: Marque } } = {};
  total = 1;
  pageSize = 10;
  pageIndex = 1;

  /**
   * default constructor of current component
   *
   * @param service
   */
  constructor(private message: NzMessageService, private service: MarqueService) {
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
    this.getMarques(pageIndex, pageSize);
  }

  /**
   * show the editable fields
   *
   * @param id
   */
  startEdit(id: number): void {
    this.editCache[id].edit = true;
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

    this.service.updateExistingMarque(this.editCache[id].data)
      .subscribe(
        () => {
          this.message.loading(`marque with id ${id} updated :) actualisation des données `);
          this.getMarques();
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
   * confirm delete marque by id
   *
   * @param id
   */
  deleteMarque(id: number) {

    this.service.deleteSingleMarque(id)
      .subscribe(
        (response: any) => {
          this.message.loading(`marque ${id} supprimée :) actualisation des données ... `);
          this.getMarques();
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
   * show modal for creating new marque
   */
  showCreateModal() {
    this.current = new Marque(0, '');
    this.isVisible = true;

  }

  /**
   * handle submit action of creating new marque
   */
  handleOk(): void {
    this.isConfirmLoading = true;

    this.service.createNewMarque(this.current).subscribe(
      () => {
        this.isConfirmLoading = false;
        this.isVisible = false;
        this.current = new Marque(0, '');
        this.message.loading(`nouveau marque  ajoutée :) actualisation des données ... `);
        this.getMarques();
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
   * get marques from database with paginable options
   */
  getMarques(pageIndex = 1, pageSize = 10, sort = 'id') {
    this.isLoading = true;
    this.service.getAllMarques(pageIndex, pageSize, sort)
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

