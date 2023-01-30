import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PRODUITS_API_URL } from './app.constants';
import { Produit } from '../models/produit.model';

@Injectable({
  providedIn: 'root'
})
export class ProduitService {

  constructor(private http: HttpClient) { }

  /**
   * Get Produits list
   *
   * @param pageNo
   * @param pageSize
   * @param sortBy
   * @returns Observable
   */
  getAllProduits(pageNo = 1, pageSize = 10, sortBy = 'id') {
    return this.http.get(`${PRODUITS_API_URL}/produits?pageNo=${pageNo - 1}&pageSize=${pageSize}&sortBy=${sortBy}`);
  }

  /**
   * Get Single Produit by it's ID
   *
   * @param id
   * @returns Observable
   */
  getSingleProduit(id: number) {
    return this.http.get(`${PRODUITS_API_URL}/produits/${id}`);
  }


  /**
 * Get Produit With Quantity 0
 *
 * @returns Observable
 */
  getReptureProduit() {
    return this.http.get(`${PRODUITS_API_URL}/produits/repture`);
  }

  /**
 * Delete Single Produit by it's ID
 *
 * @param id
 * @returns Observable
 */
  deleteSingleProduit(id: number) {
    return this.http.delete(`${PRODUITS_API_URL}/produits/${id}`);
  }

  /**
   * Create new Produit
   *
   * @param produit
   * @returns Observable
   */
  createNewProduit(produit: Produit) {
    return this.http.post(`${PRODUITS_API_URL}/produits`, produit);
  }

  /**
 * Update Existing Produit
 *
 * @param produit
 * @returns Observable
 */
  updateExistingProduit(produit: Produit) {
    return this.http.put(`${PRODUITS_API_URL}/produits/${produit.id}`, produit);
  }
}
