import { Routes } from '@angular/router';
import { ProductListComponent } from './features/catalog/product-list.component';
import { CartComponent } from './features/cart/cart.component';
import { OrdersComponent } from './features/orders/orders.component';
import { VendorDashboardComponent } from './features/vendor/vendor-dashboard.component';
import { AdminDashboardComponent } from './features/admin/admin-dashboard.component';

export const routes: Routes = [
  { path: '', component: ProductListComponent },
  { path: 'cart', component: CartComponent },
  { path: 'orders', component: OrdersComponent },
  { path: 'vendor', component: VendorDashboardComponent },
  { path: 'admin', component: AdminDashboardComponent },
];
