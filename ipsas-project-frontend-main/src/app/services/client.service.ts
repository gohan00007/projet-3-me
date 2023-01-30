import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CLIENTS_API_URL } from './app.constants';
import { Client } from '../models/client.model';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) { }

  /**
   * Get Clients list
   *
   * @param pageNo
   * @param pageSize
   * @param sortBy
   * @returns Observable
   */
  getAllClients(pageNo = 1, pageSize = 10, sortBy = 'id') {
    return this.http.get(`${CLIENTS_API_URL}/clients?pageNo=${pageNo - 1}&pageSize=${pageSize}&sortBy=${sortBy}`);
  }

  /**
   * Get Single Client by it's ID
   *
   * @param id
   * @returns Observable
   */
  getSingleClient(id: number) {
    return this.http.get(`${CLIENTS_API_URL}/clients/${id}`);
  }

  /**
 * Delete Single Client by it's ID
 *
 * @param id
 * @returns Observable
 */
  deleteSingleClient(id: number) {
    return this.http.delete(`${CLIENTS_API_URL}/clients/${id}`);
  }

  /**
   * Create new Client
   *
   * @param client
   * @returns Observable
   */
  createNewClient(client: Client) {
    return this.http.post(`${CLIENTS_API_URL}/clients`, client);
  }

  /**
 * Update Existing Client
 *
 * @param client
 * @returns Observable
 */
  updateExistingClient(client: Client) {
    return this.http.put(`${CLIENTS_API_URL}/clients/${client.id}`, client);
  }
}
