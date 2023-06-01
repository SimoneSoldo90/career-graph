import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoadmapsComponent } from './roadmaps.component';



@NgModule({
  declarations: [RoadmapsComponent],
  imports: [
    CommonModule,
  ],
  exports:[RoadmapsComponent]
})
export class RoadmapsModule { }
