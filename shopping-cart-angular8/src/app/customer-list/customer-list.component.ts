import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from "rxjs";
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  customers: Observable<Customer[]>;

  constructor(private customerService: CustomerService, private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.customers = this.customerService.getCustomersList();
    console.log("No reloadData " + JSON.stringify(this.customers));
  }

  deleteCustomer(id: string) {
    console.log("No deleteCustomer " + JSON.stringify(id));
    this.customerService.deleteCustomer(id)
      .subscribe(
        data => {
          console.log(JSON.stringify(data));
          this.reloadData();
        },
        error => console.log(error));
  }

  updateCustomer(id: string){
    console.log("No metodo updateCustomer com: " + id);
    this.router.navigate(['updateCustomer', id]);
  }

}
