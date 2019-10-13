import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  
  private baseUrl = 'http://localhost:8080/api/v1/cart';

  constructor(private http: HttpClient) { }

  createCart(cart: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/create`, cart);
  }

  getCartByCustomerId(cart: Object): Observable<Object> {
    return this.http.get(`${this.baseUrl}/cartbycustomerid`, cart);
  }

}
