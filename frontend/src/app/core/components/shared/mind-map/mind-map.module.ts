import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MindMapComponent } from './mind-map.component';
import {MatGridListModule} from '@angular/material/grid-list';



@NgModule({
  declarations: [
    MindMapComponent
  ],
  imports: [
    CommonModule,
    MatGridListModule,
  ],
  exports: [
    MindMapComponent
  ],
})
export class MindMapModule { }
