import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  template: `
  <header class="nav">
    <a routerLink="/">Shop</a>
    <nav>
      <a routerLink="/cart">Cart</a>
      <a routerLink="/orders">Orders</a>
      <a routerLink="/vendor">Vendor</a>
      <a routerLink="/admin">Admin</a>
    </nav>
  </header>
  <main class="container">
    <router-outlet></router-outlet>
  </main>
  `,
  styles: [`
    .nav{display:flex;justify-content:space-between;gap:1rem;padding:1rem;border-bottom:1px solid #ddd}
    .container{padding:1rem;max-width:1100px;margin:0 auto}
    a{text-decoration:none}
  `]
})
export class AppComponent {}
