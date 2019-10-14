import { Component, OnInit } from '@angular/core';
import { Item } from '../item';
import { Cart } from '../cart';
import { CartService } from '../cart.service';
import { ShowCartsService } from '../show-carts.service';
import { ShowCart } from '../show-carts';

@Component({
  selector: 'app-cart-list',
  templateUrl: './cart-list.component.html',
  styleUrls: ['./cart-list.component.css']
})
export class CartListComponent implements OnInit {

  cart: Cart = new Cart();
  items: Array<Item> = [];
  carts: Array<Cart> = [];
  showCarts: Array<ShowCart> = [];
  showCart: ShowCart = new ShowCart();

  constructor(private cartService: CartService, private showCartService: ShowCartsService) { }

  ngOnInit() {
    this.cartService.getAllCarts()
    .subscribe(data => {
      console.log("Carts: " + JSON.stringify(data));
      this.carts = data;
      this.showCartService.buildShowCart(this.carts);
    }, error => console.log(error));
            
    this.buildView();
  }

  buildView() {
    let showSession = sessionStorage.getItem("show");
    console.log("buildView: " + showSession);
    if(showSession != null) {
      this.showCarts = JSON.parse(showSession);
      //this.showCarts = this.showCarts.sort((a, b) => a.valorTotal - b.valorTotal);
    }
    
    console.log("showCarts: " + JSON.stringify(this.showCarts));
  }
}
