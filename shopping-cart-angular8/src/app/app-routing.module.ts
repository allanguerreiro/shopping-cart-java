import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ItemListComponent } from './item-list/item-list.component';
import { CreateItemComponent } from './create-item/create-item.component';
import { UpdateItemComponent } from './update-item/update-item.component';
import { CreateCustomerComponent } from './create-customer/create-customer.component';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { CustomerLoginComponent } from './customer-login/customer-login.component';
import { StoreComponent } from './store/store.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'store', component: StoreComponent },
  { path: 'login', component: CustomerLoginComponent },
  { path: 'items', component: ItemListComponent },
  { path: 'customers', component: CustomerListComponent },
  { path: 'createItem', component: CreateItemComponent },
  { path: 'updateItem/:id', component: UpdateItemComponent },
  { path: 'createCustomer', component: CreateCustomerComponent },
  { path: 'updateCustomer/:id', component: UpdateCustomerComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
