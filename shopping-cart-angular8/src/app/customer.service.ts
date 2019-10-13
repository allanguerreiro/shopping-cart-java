import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl = 'http://localhost:8080/api/v1/customer';

  constructor(private http: HttpClient) { }

  getCustomerByName(name: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/customerbyname/${name}`);
  }

  getCustomerByEmail(email: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/customerbyemail/${email}`);
  }

  createCustomer(customer: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/create`, customer);
  }

  updateCustomer(customer: Object): Observable<Object> {
    return this.http.put(`${this.baseUrl}/update`, customer);
  }

  deleteCustomer(id: string): Observable<any> {
    console.log("No metodo deleteItem " + JSON.stringify(id));
    return this.http.delete(`${this.baseUrl}/delete/${id}`, { responseType: 'text' });
  }

  getCustomersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/customers`);
  }
}
