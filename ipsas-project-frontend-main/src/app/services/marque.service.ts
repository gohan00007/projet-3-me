import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PRODUITS_API_URL } from './app.constants';
import { Marque } from '../models/marque.model';

@Injectable({
  providedIn: 'root'
})
export class MarqueService {

  constructor(private http: HttpClient) { }

  /**
   * Get Marques list
   *
   * @param pageNo
   * @param pageSize
   * @param sortBy
   * @returns Observable
   */
  getAllMarques(pageNo = 1, pageSize = 10, sortBy = 'id') {
    return this.http.get(`${PRODUITS_API_URL}/marques?pageNo=${pageNo - 1}&pageSize=${pageSize}&sortBy=${sortBy}`);
  }

  /**
   * Get Single Marque by it's ID
   *
   * @param id
   * @returns Observable
   */
  getSingleMarque(id: number) {
    return this.http.get(`${PRODUITS_API_URL}/marques/${id}`);
  }

  /**
 * Delete Single Marque by it's ID
 *
 * @param id
 * @returns Observable
 */
  deleteSingleMarque(id: number) {
    return this.http.delete(`${PRODUITS_API_URL}/marques/${id}`);
  }

  /**
   * Create new Marque
   *
   * @param marque
   * @returns Observable
   */
  createNewMarque(marque: Marque) {
    return this.http.post(`${PRODUITS_API_URL}/marques`, marque);
  }

  /**
 * Update Existing Marque
 *
 * @param marque
 * @returns Observable
 */
  updateExistingMarque(marque: Marque) {
    return this.http.put(`${PRODUITS_API_URL}/marques/${marque.id}`, marque);
  }
}
