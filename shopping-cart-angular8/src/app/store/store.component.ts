import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Item } from '../item';
import { ItemsService } from '../items.service';
import { Router } from '@angular/router';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit {
  
  items: Observable<Item[]>;

  constructor(private router: Router, private itemService: ItemsService, private cartSevice: CartService) { }

  ngOnInit() {
    this.items = this.itemService.getItemsList();
  }

  addCart(item: any) {
    this.cartSevice.addItem(item);
  }
}
