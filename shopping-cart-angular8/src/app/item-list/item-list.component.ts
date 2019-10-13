import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { Item } from "../item";
import { ItemsService } from '../items.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {

  items: Observable<Item[]>;

  constructor(private itemService: ItemsService, private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.items = this.itemService.getItemsList();
    console.log("No reloadData " + JSON.stringify(this.items));
  }

  deleteItem(id: string) {
    console.log("No deleteItem " + JSON.stringify(id));
    this.itemService.deleteItem(id)
      .subscribe(
        data => {
          console.log(JSON.stringify(data));
          this.reloadData();
        },
        error => console.log(error));
  }

  updateItem(id: string){
    console.log("No metodo updateItem com: " + id);
    this.router.navigate(['updateItem', id]);
  }

}
