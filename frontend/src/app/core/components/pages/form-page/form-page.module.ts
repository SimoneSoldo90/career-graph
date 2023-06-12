import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormPageComponent } from './form-page.component';
import { GenericFormModule } from '../../shared/generic-form/generic-form.module';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    FormPageComponent,
  ],
  imports: [
    CommonModule,
    GenericFormModule,
    ReactiveFormsModule,
  ],

  exports: [
    FormPageComponent,
  ]
})
export class FormPageModule { }
