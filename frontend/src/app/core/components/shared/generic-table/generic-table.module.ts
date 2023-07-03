import { NgModule, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import  {MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { CdkMenuModule } from '@angular/cdk/menu';
import { RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatTooltipModule} from '@angular/material/tooltip';

import { GenericTableComponent } from './generic-table.component';



@NgModule({
  declarations: [
    GenericTableComponent,
  ],
  imports: [
    CommonModule,
    MatTableModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatPaginatorModule,
    MatSortModule,
    CdkMenuModule,
    RouterLink,
    MatCardModule,
    MatTableModule,
  ],
  exports: [
    GenericTableComponent,
  ]
})
export class GenericTableModule {
}
