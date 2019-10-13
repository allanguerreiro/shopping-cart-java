import { Component, OnInit } from '@angular/core';
import { Item } from '../item';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemsService } from '../items.service';

@Component({
  selector: 'app-update-item',
  templateUrl: './update-item.component.html',
  styleUrls: ['./update-item.component.css']
})
export class UpdateItemComponent implements OnInit {

  id: string;
  item: Item;

  submitted = false;

  constructor(private route: ActivatedRoute, private router: Router,
    private itemService: ItemsService) { }

  ngOnInit() {
    this.item = new Item();
    console.log("No ngOnInit do update-item.components " + this.route.snapshot.params['id']);

    this.id = this.route.snapshot.params['id'];

    this.itemService.getItemById(this.id)
      .subscribe(data => {
        console.log("data" + JSON.stringify(data))
        this.item = data;
      }, error => console.log(error));
  }

  updateItem() {
    console.log("No updateItem" + JSON.stringify(this.item));
    this.itemService.updateItem(this.item)
      .subscribe(data => console.log(JSON.stringify(data)), error => console.log(error));
    this.item = new Item();
    this.gotoList();
  }

  onSubmit() {
    this.updateItem();    
  }

  gotoList() {
    this.router.navigate(['/items']);
  }

}
