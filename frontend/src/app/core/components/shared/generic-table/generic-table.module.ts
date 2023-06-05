import { NgModule, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatTableModule} from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';

import { GenericTableComponent } from './generic-table.component';



@NgModule({
  declarations: [
    GenericTableComponent,
  ],
  imports: [
    CommonModule,
    MatTableModule,
    MatIconModule,
  ],
  exports: [
    GenericTableComponent,
  ]
})
export class GenericTableModule {
}
