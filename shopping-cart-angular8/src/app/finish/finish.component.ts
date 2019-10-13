import { Component, OnInit } from '@angular/core';
import { Cart } from '../cart';

@Component({
  selector: 'app-finish',
  templateUrl: './finish.component.html',
  styleUrls: ['./finish.component.css']
})
export class FinishComponent implements OnInit {

  protocolNumber: string;
  cart: Cart = new Cart();
  constructor() { }

  ngOnInit() {
    let cartSession = sessionStorage.getItem("cart");
    console.log("ngOnInit: " + cartSession);
    if(cartSession != null) {
      this.cart = JSON.parse(cartSession);
      this.protocolNumber = this.cart.id;
    } else {
      this.protocolNumber = "Oops! Your protocol number had a slight problem, but don't worry, your purchase is totally secure. ;)"
    }    
  }
}
