import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoadmaptreeComponent } from './roadmaptree.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';



@NgModule({
  declarations: [
    RoadmaptreeComponent
  ],
  imports: [
    CommonModule,
    MatIconModule,
    MatButtonModule,
  ],
  exports:[RoadmaptreeComponent]
})
export class RoadmaptreeModule { }
