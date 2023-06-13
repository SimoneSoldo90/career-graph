import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatMenuModule } from '@angular/material/menu';
import { NavlargemenuComponent } from './navlargemenu.component';
import { MenuItemModule } from '../menu-item/menu-item.module';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [NavlargemenuComponent],
  imports: [
    CommonModule,
    MatMenuModule,
    MenuItemModule,
    MatButtonModule,
    MatIconModule,
    RouterModule,
  ],
  exports:[NavlargemenuComponent]
})
export class NavlargemenuModule { }
