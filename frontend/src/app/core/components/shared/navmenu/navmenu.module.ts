import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatMenuModule } from '@angular/material/menu';
import { NavmenuComponent } from './navmenu.component';
import { MenuItemModule } from '../menu-item/menu-item.module';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@NgModule({
  declarations: [NavmenuComponent],
  imports: [
    CommonModule,
    MatMenuModule,
    MenuItemModule,
    MatButtonModule,
    MatIconModule,
  ],
  exports:[NavmenuComponent]
})
export class NavmenuModule { }
