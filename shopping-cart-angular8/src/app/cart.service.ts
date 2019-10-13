import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Item } from './item';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  
  private baseUrl = 'http://localhost:8080/api/v1/cart';

  newItems: Array<Item> = [];

  constructor(private http: HttpClient) {
   }

  createCart(cart: Object): Observable<any> {
    return this.http.post(`${this.baseUrl}/create`, cart);
  }

  getCartByCustomerId(cart: Object): Observable<Object> {
    return this.http.get(`${this.baseUrl}/cartbycustomerid`, cart);
  }

  public addItem(item: Item): void {
    console.log("lista: " + JSON.stringify(this.newItems));
    console.log("no addItem do service: " + JSON.stringify(item));
    let product = this.newItems.find(i => i.id === item.id);
    console.log("no addItem do service product: " + JSON.stringify(product));
    if (product === undefined) {
      product = new Item();
      product.id = item.id;
      product.value = item.value;
      product.name = item.name;
      this.newItems.push(product);
    } else {
      for( var i = 0; i < this.newItems.length; i++){
        if (this.newItems[i].id === item.id) {
          this.newItems[i].value += this.newItems[i].value;
        }
      }
    }
    
    console.log("Tamanho da lista: " + this.newItems.length);
    sessionStorage.clear();
    sessionStorage.setItem("cart", JSON.stringify(this.newItems));
  }

  public remItem(item: Item): void {
    console.log("no remov lista: " + JSON.stringify(this.newItems));
    for( var i = 0; i < this.newItems.length; i++){
      if (this.newItems[i].id === item.id) {
        this.newItems.splice(i, 1); 
      }
    }
    sessionStorage.clear();
    sessionStorage.setItem("cart", JSON.stringify(this.newItems));
  }
}
