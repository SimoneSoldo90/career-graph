import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoadmapsComponent } from './roadmaps.component';
import { GenericTableModule } from '../../shared/generic-table/generic-table.module';



@NgModule({
  declarations: [RoadmapsComponent],
  imports: [
    CommonModule,
    GenericTableModule,
  ],
  exports:[
    RoadmapsComponent
  ]
})
export class RoadmapsModule { }
