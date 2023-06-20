import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenteesComponent } from './mentees.component';
import { GenericTableModule } from '../../shared/generic-table/generic-table.module';



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
