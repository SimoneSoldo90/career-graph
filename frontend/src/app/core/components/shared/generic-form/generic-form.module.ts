import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GenericFormComponent } from './generic-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatCheckboxModule} from '@angular/material/checkbox';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { HttpClientModule } from '@angular/common/http';




@NgModule({
  declarations: [
    GenericFormComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule,
    MatSlideToggleModule,
    MatCheckboxModule,
    MatOptionModule,
    MatSelectModule,
    HttpClientModule,
  ],
  exports: [
    GenericFormComponent,
  ]
})
export class GenericFormModule { }
