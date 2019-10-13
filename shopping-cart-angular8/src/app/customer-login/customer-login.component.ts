import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-customer-login',
  templateUrl: './customer-login.component.html',
  styleUrls: ['./customer-login.component.css']
})
export class CustomerLoginComponent implements OnInit {

  customer: Customer = new Customer();

  constructor(private route: ActivatedRoute, private router: Router,
    private customerService: CustomerService) { }

  ngOnInit() {
    
  }

  findCustomerByEmail(email: string) {    
    console.log("email: " + email);
    
    this.customerService.getCustomerByEmail(email)
    .subscribe(data => {
      console.log("data" + JSON.stringify(data));
      this.customer = data;
      this.update()
    }, error => console.log("Error: " + JSON.stringify(error)));

    this.update()
    this.makeDecision();
  }

  update(){
    console.log(this.customer);
  }

  makeDecision() {
    if(this.customer.name === undefined) {
      alert("Email not founded! Please Sign up!");
    } else {
      this.gotoStore();
    } 
  }

  createCustomer() {
    this.router.navigate(['/createCustomer']);
  }

  gotoStore() {
    this.router.navigate(['/store']);
  }
}
