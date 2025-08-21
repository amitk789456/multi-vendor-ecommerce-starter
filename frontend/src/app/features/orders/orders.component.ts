import { Component } from '@angular/core';
import axios from 'axios';

@Component({
  selector: 'app-orders',
  standalone: true,
  template: `
  <h2>Orders</h2>
  <button (click)="pay()">Pay ₹500 (demo)</button>
  `
})
export class OrdersComponent {
  base = 'http://localhost:8080';
  async pay(){
    const r = await axios.post(this.base + '/api/orders/pay?amount=500&currency=INR');
    alert('Payment created with ' + r.data.provider + ' reference ' + r.data.reference);
  }
}
