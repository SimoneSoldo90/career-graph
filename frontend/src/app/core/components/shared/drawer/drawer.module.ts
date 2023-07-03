import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DrawerComponent } from './drawer.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { StatusManagerModule } from '../status-manager/status-manager.module';
import { MatIconModule } from '@angular/material/icon';

@NgModule({
  declarations: [DrawerComponent],
  imports: [CommonModule, MatSidenavModule, StatusManagerModule,MatIconModule],
  exports: [DrawerComponent]
})
export class DrawerModule { }

