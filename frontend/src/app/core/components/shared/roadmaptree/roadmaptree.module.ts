import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoadmaptreeComponent } from './roadmaptree.component';



@NgModule({
  declarations: [
    RoadmaptreeComponent
  ],
  imports: [
    CommonModule
  ],
  exports:[RoadmaptreeComponent]
})
export class RoadmaptreeModule { }
