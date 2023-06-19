import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GenericTableModule } from '../../shared/generic-table/generic-table.module';
import { MenteesComponent } from './mentees.component';



@NgModule({
  declarations: [MenteesComponent],
  imports: [
    CommonModule,
    GenericTableModule,
  ],
  exports: [
    MenteesComponent
  ]
})
export class MenteesModule { }
