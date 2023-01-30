import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { REGLEMENTS_API_URL } from './app.constants';
import { Reglement } from '../models/reglement.model';

@Injectable({
  providedIn: 'root'
})
export class ReglementService {

  constructor(private http: HttpClient) { }

  /**
   * Get Reglements list
   *
   * @param pageNo
   * @param pageSize
   * @param sortBy
   * @returns Observable
   */
  getAllReglements(pageNo = 1, pageSize = 10, sortBy = 'id') {
    return this.http.get(`${REGLEMENTS_API_URL}/reglements?pageNo=${pageNo - 1}&pageSize=${pageSize}&sortBy=${sortBy}`);
  }

  /**
   * Get Single Reglement by it's ID
   *
   * @param id
   * @returns Observable
   */
  getSingleReglement(id: number) {
    return this.http.get(`${REGLEMENTS_API_URL}/reglements/${id}`);
  }

  /**
 * Delete Single Reglement by it's ID
 *
 * @param id
 * @returns Observable
 */
  deleteSingleReglement(id: number) {
    return this.http.delete(`${REGLEMENTS_API_URL}/reglements/${id}`);
  }

  /**
   * Create new Reglement
   *
   * @param reglement
   * @returns Observable
   */
  createNewReglement(reglement: Reglement) {
    return this.http.post(`${REGLEMENTS_API_URL}/reglements`, reglement);
  }

  /**
 * Update Existing Reglement
 *
 * @param reglement
 * @returns Observable
 */
  updateExistingReglement(reglement: Reglement) {
    return this.http.put(`${REGLEMENTS_API_URL}/reglements/${reglement.id}`, reglement);
  }
}
