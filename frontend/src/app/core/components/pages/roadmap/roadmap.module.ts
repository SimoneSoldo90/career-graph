import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoadmapComponent } from './roadmap.component';
import { GenericTableModule } from '../../shared/generic-table/generic-table.module';



@NgModule({
  declarations: [RoadmapComponent],
  imports: [
    CommonModule,
    GenericTableModule,
  ],
  exports:[
    RoadmapComponent
  ]
})
export class RoadmapModule { }
