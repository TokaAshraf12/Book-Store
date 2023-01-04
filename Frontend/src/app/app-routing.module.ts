import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { AddItemComponent } from './add-item/add-item.component';
import { CartComponent } from './cart/cart.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { ProductComponent } from './product/product.component';
import { AddPublisherComponent } from './add-publisher/add-publisher.component';
import { PublisherComponent } from './publisher/publisher.component';
import { PublisherListComponent } from './publisher-list/publisher-list.component';
import { OrdersComponent } from './orders/orders.component';



const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'user/profile', component: ProfileComponent },
  { path: 'home', component: HomeComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'user/add-item', component: AddItemComponent },
  { path: 'user/cart' , component:CartComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'checkout', component: CheckoutComponent },
  { path : 'product' , component: ProductComponent},
  { path: '', redirectTo: '/checkout', pathMatch: 'full'},
  { path: 'user/add-publisher', component: AddPublisherComponent },
  { path : 'publisher' , component: PublisherComponent},
  { path : 'user/view-publishers' , component: PublisherListComponent},
  { path : 'user/view-orders' , component: OrdersComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],  
  exports: [RouterModule],
})
export class AppRoutingModule {}
export const routingComponents = [
  LoginComponent,
  ProfileComponent,
  HomeComponent,
  RegisterComponent,
  AddItemComponent,
  CartComponent,
  ProductComponent,
  CheckoutComponent,
  AddPublisherComponent,
  PublisherComponent,
  PublisherListComponent
];
