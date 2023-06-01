import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GenericFormComponent } from './generic-form.component';




@NgModule({
  declarations: [
    GenericFormComponent,
  ],
  imports: [
    CommonModule
  ],
  exports: [
    GenericFormComponent,
  ]
})
export class GenericFormModule { }
