import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MenuItemComponent } from './menu-item.component';



@NgModule({
  declarations: [MenuItemComponent],
  imports: [
    CommonModule,
    RouterModule,
  ],
  exports:[MenuItemComponent]
})
export class MenuItemModule { }
