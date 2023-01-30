import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PRODUITS_API_URL } from './app.constants';
import { Categorie } from '../models/categorie.model';

@Injectable({
  providedIn: 'root'
})
export class CategorieService {

  constructor(private http: HttpClient) { }

  /**
   * Get Categories list
   *
   * @param pageNo
   * @param pageSize
   * @param sortBy
   * @returns Observable
   */
  getAllCategories(pageNo = 1, pageSize = 10, sortBy = 'id') {
    return this.http.get(`${PRODUITS_API_URL}/categories?pageNo=${pageNo - 1}&pageSize=${pageSize}&sortBy=${sortBy}`);
  }

  /**
   * Get Single Categorie by it's ID
   *
   * @param id
   * @returns Observable
   */
  getSingleCategorie(id: number) {
    return this.http.get(`${PRODUITS_API_URL}/categories/${id}`);
  }

  /**
 * Delete Single Categorie by it's ID
 *
 * @param id
 * @returns Observable
 */
  deleteSingleCategorie(id: number) {
    return this.http.delete(`${PRODUITS_API_URL}/categories/${id}`);
  }

  /**
   * Create new Categorie
   *
   * @param categorie
   * @returns Observable
   */
  createNewCategorie(categorie: Categorie) {
    return this.http.post(`${PRODUITS_API_URL}/categories`, categorie);
  }

  /**
 * Update Existing Categorie
 *
 * @param categorie
 * @returns Observable
 */
  updateExistingCategorie(categorie: Categorie) {
    return this.http.put(`${PRODUITS_API_URL}/categories/${categorie.id}`, categorie);
  }
}
