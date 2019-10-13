import { Component, OnInit } from '@angular/core';
import { Item } from "../item";
import { ItemsService } from '../items.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  newItems: Array<Item> = [];
  item: Item = new Item();
  isDisabled = true;

  constructor(private route: ActivatedRoute, private router: Router, private itemService: ItemsService) { }

  ngOnInit() {
    
  }

  reloadData(newItems: any) {
    console.log("NO Reload: " + JSON.stringify(newItems));
  }

  addCart(item: any){
    this.newItems.push(item);
    this.reloadData(this.newItems);
    this.isDisabled = true;
  }

  remCart(item: any){
    console.log("No remCart: " + item);
    if(this.newItems.length <= 0) {
      this.isDisabled = false;
    }
  }
}
