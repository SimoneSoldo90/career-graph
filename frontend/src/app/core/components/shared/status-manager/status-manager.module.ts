import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatButtonToggleModule } from '@angular/material/button-toggle';
import { StatusManagerComponent } from './status-manager.component';
import { MatSelectModule } from '@angular/material/select';
import { MatMenuModule } from '@angular/material/menu';



@NgModule({
  declarations: [StatusManagerComponent],
  imports: [
    CommonModule,
    MatButtonToggleModule,
    MatSelectModule,
    MatMenuModule
  ],
  exports: [StatusManagerComponent]
})
export class StatusManagerModule { }
