import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ItemListComponent } from './item-list/item-list.component';
import { CreateItemComponent } from './create-item/create-item.component';
import { FormsModule } from '@angular/forms';
import { UpdateItemComponent } from './update-item/update-item.component';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';
import { CreateCustomerComponent } from './create-customer/create-customer.component';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { StoreComponent } from './store/store.component';
import { CustomerLoginComponent } from './customer-login/customer-login.component';
import { CartComponent } from './cart/cart.component';

@NgModule({
  declarations: [
    AppComponent,
    ItemListComponent,
    CreateItemComponent,
    UpdateItemComponent,
    UpdateCustomerComponent,
    CreateCustomerComponent,
    CustomerListComponent,
    StoreComponent,
    CustomerLoginComponent,
    CartComponent    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }