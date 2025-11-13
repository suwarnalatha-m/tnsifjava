import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ShopOwnerService {
  API = "http://localhost:8082";

  constructor(private http: HttpClient) {}

  registerShopOwners(shopOwnerData: any) {
    return this.http.post(`${this.API}/shopOwners`, shopOwnerData);
  }

  getShopOwners() {
    return this.http.get(`${this.API}/shopOwners`);
  }

  deleteShopOwners(ShopOwnerId: number) {
    return this.http.delete(`${this.API}/shopOwners/${ShopOwnerId}`);
  }

  // Update an order
  public updateShopOwners(shopOwner: any) {
    const ShopOwnerId = shopOwner.id || shopOwner.ShopOwnerId; // Ensure compatibility
    return this.http.put(`${this.API}/shopOwners/${ShopOwnerId}`, shopOwner); // Adjusted to match backend endpoint
  }
}
