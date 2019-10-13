import { Component, OnInit } from '@angular/core';
import { Item } from "../item";
import { Router } from '@angular/router';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  newItems: Array<Item> = [];
  item: Item = new Item();
  isDisabled = true;
  
  constructor(private router: Router, private cartService: CartService ) {}

  ngOnInit() {
    sessionStorage.clear();
  }

  reloadData(newItems: any) {
    console.log("NO Reload: " + JSON.stringify(newItems));
  }

  reload(){
    let cartSession = sessionStorage.getItem("cart");
    console.log("Da session: " + cartSession);

    if(cartSession != null) {
      this.newItems = JSON.parse(cartSession);
      this.isDisabled = false;
      if(this.newItems.length <= 0) {
        this.isDisabled = true;
        sessionStorage.clear();
      }
    } else {
      sessionStorage.clear();
      sessionStorage.setItem("cart", JSON.stringify(this.newItems));
    }
  }

  checkout() {
    console.log("No checkout: " + JSON.stringify(this.newItems));
    this.gotoCheckout();
  }

  remCart(item: any) {
    console.log("No remCart: " + item);
    for( var i = 0; i < this.newItems.length; i++){
      if (this.newItems[i].id === item.id) {
        this.newItems.splice(i, 1); 
      }
    }
    
    this.cartService.remItem(item);
    
    console.log("ATamanho da lista de compras: " + this.newItems.length);
    if(this.newItems.length <= 0) {
      this.isDisabled = true;
      sessionStorage.clear();
    }
  }

  gotoCheckout() {
    this.router.navigate(['/checkout']);
  }
}
