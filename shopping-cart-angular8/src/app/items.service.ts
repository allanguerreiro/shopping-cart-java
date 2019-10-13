import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ItemsService {
  
  private baseUrl = 'http://localhost:8080/api/v1/item';

  constructor(private http: HttpClient) { }

  getItemById(id: string): Observable<any> {
    console.log("No metodo getItemById " + id);
    return this.http.get(`${this.baseUrl}/itembyid/${id}`);
  }

  createItem(item: Object): Observable<Object> {
    console.log("No metodo createItem " + JSON.stringify(item));
    return this.http.post(`${this.baseUrl}/create`, item);
  }

  updateItem(item: Object): Observable<Object> {
    console.log("No metodo updateItem " + JSON.stringify(item));
    return this.http.put(`${this.baseUrl}/update`, item);
  }

  deleteItem(id: string): Observable<any> {
    console.log("No metodo deleteItem " + JSON.stringify(id));
    return this.http.delete(`${this.baseUrl}/delete/${id}`, { responseType: 'text' });
  }

  getItemsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/items`);
  }
}
