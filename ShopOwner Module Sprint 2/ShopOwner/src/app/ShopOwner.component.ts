import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ShopOwnerService } from './ShopOwner.service';

interface ShopOwner {
  id: number | null;
  name: string;
  shopName: string;
  contactInfo: string;
}

@Component({
  selector: 'app-shop-owner-root',
  templateUrl: './ShopOwner.component.html',
  styleUrls: ['./ShopOwner.component.scss']
})
export class ShopOwnerComponent implements OnInit {
  title = 'shopowners';

  constructor(private shopOwnerService: ShopOwnerService) {}

  shopOwners: ShopOwner[] = [];
  shopOwnerToUpdate: ShopOwner = {
    id: null,
    name: '',
    shopName: '',
    contactInfo: ''
  };

  ngOnInit(): void {
    this.getShopOwners();
  }

  /**
   * Registers a new ShopOwner using the provided form data.
   * @param registerForm The form containing shop owner data.
   */
  register(registerForm: NgForm): void {
    this.shopOwnerService.registerShopOwners(registerForm.value).subscribe(
      () => {
        registerForm.reset();
        this.getShopOwners();
      },
      error => console.error('Error registering Shop Owner:', error)
    );
  }

  /**
   * Fetches all shop owners from the backend and updates the local state.
   */
  getShopOwners(): void {
    this.shopOwnerService.getShopOwners().subscribe(
      (response: any) => {
        if (response._embedded?.shopOwners) {
          this.shopOwners = response._embedded.shopOwners.map((shopOwner: any) => ({
            id: shopOwner.id, // Ensure this matches backend data field
            name: shopOwner.name || '',
            shopName: shopOwner.shopName || '',
            contactInfo: shopOwner.contactInfo || ''
          }));
        } else {
          // Handle empty or missing embedded data
          this.shopOwners = [];
        }
      },
      error => console.error('Error fetching shop owners:', error)
    );
  }

  /**
   * Deletes a shop owner by ID.
   * @param shopOwner The shop owner to delete.
   */
  deleteShopOwner(shopOwner: ShopOwner): void {
    if (!shopOwner.id) {
      console.error('Shop Owner ID is undefined. Cannot delete shop owner.', shopOwner);
      return;
    }
    this.shopOwnerService.deleteShopOwners(shopOwner.id).subscribe(
      () => this.getShopOwners(),
      error => console.error('Error deleting Shop Owner:', error)
    );
  }

  /**
   * Sets the shopOwnerToUpdate object for editing.
   * @param shopOwner The shop owner to edit.
   */
  edit(shopOwner: ShopOwner): void {
    this.shopOwnerToUpdate = { ...shopOwner };
  }

  /**
   * Updates an existing shop owner with the current shopOwnerToUpdate values.
   */
  updateShopOwner(): void {
    if (this.shopOwnerToUpdate.id) {
      this.shopOwnerService.updateShopOwners(this.shopOwnerToUpdate).subscribe(
        () => {
          this.getShopOwners();
          this.shopOwnerToUpdate = {
            id: null,
            name: '',
            shopName: '',
            contactInfo: ''
          }; // Reset after successful update
        },
        error => console.error('Error updating Shop Owner:', error)
      );
    } else {
      console.error('Shop Owner ID is undefined. Cannot update shop owner.');
    }
  }
}  