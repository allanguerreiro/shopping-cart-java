import { Injectable } from '@angular/core';
import { ShowCart } from './show-carts';
import { Cart } from './cart';

@Injectable({
  providedIn: 'root'
})
export class ShowCartsService {
  
  showCart: ShowCart = new ShowCart();
  showCarts: Array<ShowCart> = [];
  cart: Cart = new Cart();
  total = 0;

  constructor() { }

  public buildShowCart(carts: any): void {
    console.log("buildShowCart: " + JSON.stringify(carts));
    for(var i = 0; i < carts.length; i++) {
      this.cart = carts[i];
      this.showCart.cartId = this.cart.id;
      this.showCart.customerId = this.cart.customerId;
      this.showCart.items = this.cart.items;
      this.showCart.valorTotal = this.getTotal(this.cart.items);
      this.showCarts.push(this.showCart);
      console.log("this.showCart: " + JSON.stringify(this.showCart));
    }
    
    sessionStorage.setItem("show", JSON.stringify(this.showCarts));
    console.log("this.showCarts: " + JSON.stringify(this.showCarts));
  }

  getTotal(items: any): number {
    for(var i = 0; i < items.length; i++) {
      this.total += items[i].value;
    }
    return this.total;
  }
}
