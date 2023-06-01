import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GenericTableComponent } from './generic-table.component';
import { GenericFormComponent } from '../generic-form/generic-form.component';



@NgModule({
  declarations: [
    GenericTableComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    GenericTableComponent
  ]
})
export class GenericTableModule { }
