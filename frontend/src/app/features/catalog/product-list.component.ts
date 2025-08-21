import { Component, OnInit } from '@angular/core';
import axios from 'axios';

@Component({
  selector: 'app-product-list',
  standalone: true,
  template: `
    <h2>Products</h2>
    <input placeholder="Search..." (input)="onSearch($event)">
    <div class="grid">
      <article *ngFor="let p of products">
        <img *ngIf="p.imageUrl" [src]="p.imageUrl" width="160" height="120">
        <h3>{{p.title}}</h3>
        <p>₹ {{p.price}}</p>
        <button (click)="addToCart(p.id)">Add to Cart</button>
      </article>
    </div>
  `,
  styles: [`
    .grid{display:grid;grid-template-columns:repeat(auto-fill,minmax(220px,1fr));gap:1rem}
    article{border:1px solid #eee;padding:1rem;border-radius:12px}
    img{object-fit:cover;border-radius:8px}
  `]
})
export class ProductListComponent implements OnInit {
  products:any[] = [];
  base = 'http://localhost:8080';

  ngOnInit() {
    this.load();
  }

  async load(q:string='') {
    const r = await axios.get(this.base + '/api/products', { params: q? { q } : {} });
    this.products = r.data;
  }

  onSearch(e:any) {
    const q = e.target.value;
    this.load(q);
  }

  async addToCart(id:number){
    await axios.post(this.base + '/api/cart/add', { productId:id, quantity:1 });
    alert('Added to cart');
  }
}
