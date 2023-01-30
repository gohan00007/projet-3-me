import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PRODUITS_API_URL } from './app.constants';
import { Taxe } from '../models/taxe.model';

@Injectable({
  providedIn: 'root'
})
export class TaxeService {

  constructor(private http: HttpClient) { }

  /**
   * Get Taxes list
   *
   * @param pageNo
   * @param pageSize
   * @param sortBy
   * @returns Observable
   */
  getAllTaxes(pageNo = 1, pageSize = 10, sortBy = 'id') {
    return this.http.get(`${PRODUITS_API_URL}/taxes?pageNo=${pageNo - 1}&pageSize=${pageSize}&sortBy=${sortBy}`);
  }

  /**
   * Get Single Taxe by it's ID
   *
   * @param id
   * @returns Observable
   */
  getSingleTaxe(id: number) {
    return this.http.get(`${PRODUITS_API_URL}/taxes/${id}`);
  }

  /**
 * Delete Single Taxe by it's ID
 *
 * @param id
 * @returns Observable
 */
  deleteSingleTaxe(id: number) {
    return this.http.delete(`${PRODUITS_API_URL}/taxes/${id}`);
  }

  /**
   * Create new Taxe
   *
   * @param taxe
   * @returns Observable
   */
  createNewTaxe(taxe: Taxe) {
    return this.http.post(`${PRODUITS_API_URL}/taxes`, taxe);
  }

  /**
 * Update Existing Taxe
 *
 * @param taxe
 * @returns Observable
 */
  updateExistingTaxe(taxe: Taxe) {
    return this.http.put(`${PRODUITS_API_URL}/taxes/${taxe.id}`, taxe);
  }
}
