import { Component } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzTableQueryParams } from 'ng-zorro-antd/table';
import { Categorie } from 'src/app/models/categorie.model';
import { CategorieService } from 'src/app/services/categorie.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent {

  // local variables
  current: Categorie = new Categorie(0, '');
  listOfData: Categorie[] = [];

  isVisible = false;
  isConfirmLoading = false;
  isLoading = true;
  editCache: { [key: string]: { edit: boolean; data: Categorie } } = {};
  total = 1;
  pageSize = 10;
  pageIndex = 1;

  /**
   * default constructor of current component
   *
   * @param service
   */
  constructor(private message: NzMessageService, private service: CategorieService) {
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
    this.getCategories(pageIndex, pageSize);
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
    // editCache[data.id].data
    Object.assign(this.listOfData[index], this.editCache[id].data);
    this.editCache[id].edit = false;

    this.service.updateExistingCategorie(this.editCache[id].data)
      .subscribe(
        () => {
          this.message.loading(`categorie with id ${id} updated :) actualisation des données `);
          this.getCategories();
        },
        (error: any) => {
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
   * confirm delete categorie by id
   *
   * @param id
   */
  deleteCategorie(id: number) {

    this.service.deleteSingleCategorie(id)
      .subscribe(
        (response: any) => {
          this.message.loading(`categorie ${id} supprimée :) actualisation des données ... `);
          this.getCategories();
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
   * show modal for creating new categorie
   */
  showCreateModal() {
    this.current = new Categorie(0, '');
    this.isVisible = true;

  }

  /**
   * handle submit action of creating new categorie
   */
  handleOk(): void {
    this.isConfirmLoading = true;

    this.service.createNewCategorie(this.current).subscribe(
      () => {
        this.isConfirmLoading = false;
        this.isVisible = false;
        this.current = new Categorie(0, '');
        this.message.loading(`nouveau categorie  ajoutée :) actualisation des données ... `);
        this.getCategories();
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
   * get categories from database with paginable options
   */
  getCategories(pageIndex = 1, pageSize = 10, sort = 'id') {
    this.isLoading = true;
    this.service.getAllCategories(pageIndex, pageSize, sort)
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

