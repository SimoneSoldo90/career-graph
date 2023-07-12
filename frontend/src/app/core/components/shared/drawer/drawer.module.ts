import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DrawerComponent } from './drawer.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { StatusManagerModule } from '../status-manager/status-manager.module';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
  declarations: [DrawerComponent],
  imports: [CommonModule, MatSidenavModule, StatusManagerModule,MatIconModule,MatButtonModule],
  exports: [DrawerComponent]
})
export class DrawerModule { }

