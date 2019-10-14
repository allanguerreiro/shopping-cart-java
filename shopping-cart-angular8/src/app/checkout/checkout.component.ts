import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';
import { CartService } from '../cart.service';
import { Item } from '../item';
import { Cart } from '../cart';
import { Router } from '@angular/router';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  customer: Customer = new Customer();
  items: Array<Item> = [];
  customerName: string;
  cart: Cart = new Cart;
  total = 0;

  constructor(private router: Router, private customerService: CustomerService, private cartService: CartService) { }

  ngOnInit() {
    let cartSession = sessionStorage.getItem("cart");
    console.log("Da session anterior: " + cartSession);
    if(cartSession != null) {
      this.items = JSON.parse(cartSession);
      this.items = this.items.sort((a, b) => a.value - b.value);
    }
    
    for(var i = 0; i < this.items.length; i++) {
      this.total += this.items[i].value;
    }
    console.log("O Total e: " + this.total);
  }

findCustomerByEmail() {
    this.customerService.getCustomerByEmail(this.customer.email)
      .subscribe(data => {
        console.log("data " + JSON.stringify(data));
        this.customer = data;
        sessionStorage.setItem("customer", JSON.stringify(this.customer));
        this.customerName = this.customer.name;
      }, error => console.log(error));
  }

  onSubmit() {
    this.findCustomerByEmail();
  }

  buy() {
    this.cart.customerId = this.customer.id;
    this.cart.items = this.items;
    console.log("Cart JSON: " + JSON.stringify(this.cart));
    this.cartService.createCart(this.cart)
      .subscribe(data => {
        console.log("data " + JSON.stringify(data));
        this.cart = data;
        sessionStorage.setItem("cart", JSON.stringify(this.cart));
      }, error => console.log(error));
      this.gotoCheckout();
  }

  gotoCheckout() {
    this.router.navigate(['/finish']);
  }
}
