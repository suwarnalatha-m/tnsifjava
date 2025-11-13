import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { ShopOwnerComponent } from './ShopOwner.component';




@NgModule({
  declarations: [
    ShopOwnerComponent 
  ],
  imports: [
    BrowserModule,  
    FormsModule,    
    HttpClientModule 
  ],
  providers: [],
  bootstrap: [ShopOwnerComponent] 
})
export class ShopOwnerModule {}
