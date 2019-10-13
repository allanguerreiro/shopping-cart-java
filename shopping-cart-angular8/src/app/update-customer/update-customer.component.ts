import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent implements OnInit {

  name: string;
  customer: Customer;

  submitted = false;

  constructor(private route: ActivatedRoute, private router: Router,
    private customerService: CustomerService) { }

  ngOnInit() {
    this.customer = new Customer();

    this.name = this.route.snapshot.params['name'];

    this.customerService.getCustomerByName(this.name)
      .subscribe(data => {
        console.log("data" + JSON.stringify(data))
        this.customer = data;
      }, error => console.log(error));
  }

  updateCustomer() {
    console.log("No updateCustomer" + JSON.stringify(this.customer));
    this.customerService.updateCustomer(this.customer)
      .subscribe(data => console.log(JSON.stringify(data)), error => console.log(error));
    this.customer = new Customer();
    this.gotoList();
  }

  onSubmit() {
    this.updateCustomer();    
  }

  gotoList() {
    this.router.navigate(['/customers']);
  }

}
