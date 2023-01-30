import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FACTURES_API_URL } from './app.constants';
import { Facture } from '../models/facture.model';

@Injectable({
  providedIn: 'root'
})
export class FactureService {

  constructor(private http: HttpClient) { }

  /**
   * Get Factures list
   *
   * @param pageNo
   * @param pageSize
   * @param sortBy
   * @returns Observable
   */
  getAllFactures(pageNo = 1, pageSize = 10, sortBy = 'id') {
    return this.http.get(`${FACTURES_API_URL}/factures?pageNo=${pageNo - 1}&pageSize=${pageSize}&sortBy=${sortBy}`);
  }

  /**
   * Get Single Facture by it's ID
   *
   * @param id
   * @returns Observable
   */
  getSingleFacture(id: number) {
    return this.http.get(`${FACTURES_API_URL}/factures/${id}`);
  }

  /**
 * Delete Single Facture by it's ID
 *
 * @param id
 * @returns Observable
 */
  deleteSingleFacture(id: number) {
    return this.http.delete(`${FACTURES_API_URL}/factures/${id}`);
  }

  /**
   * Create new Facture
   *
   * @param facture
   * @returns Observable
   */
  createNewFacture(facture: Facture) {
    return this.http.post(`${FACTURES_API_URL}/factures`, facture);
  }

  /**
 * Update Existing Facture
 *
 * @param facture
 * @returns Observable
 */
  updateExistingFacture(facture: Facture) {
    return this.http.put(`${FACTURES_API_URL}/factures/${facture.id}`, facture);
  }

  getChiffreAffaireClientGlobale(id: number) {
    return this.http.get(`${FACTURES_API_URL}/factures/clients/${id}/chiffre-affaire`);
  }

  getChiffreAffaireClientAnnee(id: number, annee: number) {
    return this.http.get(`${FACTURES_API_URL}/factures/clients/${id}/chiffre-affaire-annee/${annee}`);
  }

  getClientReste(id: number) {
    return this.http.get(`${FACTURES_API_URL}/factures/clients/${id}/reste`);
  }

  getClientListePayee(id: number) {
    return this.http.get(`${FACTURES_API_URL}/factures/clients/${id}/liste-payee`);
  }

  getClientListeNonPayee(id: number) {
    return this.http.get(`${FACTURES_API_URL}/factures/clients/${id}/liste-non-payee`);
  }

  getListePayee() {
    return this.http.get(`${FACTURES_API_URL}/factures/payee`);
  }

  getListeNonPayee() {
    return this.http.get(`${FACTURES_API_URL}/factures/non-payee`);
  }

}
