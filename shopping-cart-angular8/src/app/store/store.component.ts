import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { Item } from "../item";
import { ItemsService } from '../items.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit {

  items: Observable<Item[]>;
  item: Item = new Item();
  newItems: Array<Item> = [];

  constructor(private route: ActivatedRoute, private router: Router,
    private itemService: ItemsService) { }

  ngOnInit() {
    this.items = this.itemService.getItemsList();
  }

  addCart(item: any){
    console.log("No addCart: " + JSON.stringify(item));
    this.newItems.push(item);
    console.log("No addCart: " + JSON.stringify(this.newItems));
  }

  remCart(){
    console.log("No remCart: ");
  }
}
