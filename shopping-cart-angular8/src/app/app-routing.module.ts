import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ItemListComponent } from './item-list/item-list.component';
import { CreateItemComponent } from './create-item/create-item.component';
import { UpdateItemComponent } from './update-item/update-item.component';
import { CreateCustomerComponent } from './create-customer/create-customer.component';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { StoreComponent } from './store/store.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { FinishComponent } from './finish/finish.component';
import { CartListComponent } from './cart-list/cart-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'store', pathMatch: 'full' },
  { path: 'store', component: StoreComponent },
  { path: 'carts', component: CartListComponent },
  { path: 'finish', component: FinishComponent },
  { path: 'items', component: ItemListComponent },
  { path: 'customers', component: CustomerListComponent },
  { path: 'createItem', component: CreateItemComponent },
  { path: 'updateItem/:id', component: UpdateItemComponent },
  { path: 'createCustomer', component: CreateCustomerComponent },
  { path: 'updateCustomer/:id', component: UpdateCustomerComponent },
  { path: 'checkout', component: CheckoutComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
