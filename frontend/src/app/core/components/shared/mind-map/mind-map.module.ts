import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MindMapComponent } from './mind-map.component';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';



@NgModule({
  declarations: [
    MindMapComponent
  ],
  imports: [
    CommonModule,
    MatGridListModule,
    MatSlideToggleModule,
  ],
  exports: [
    MindMapComponent,
  ],
})
export class MindMapModule { }
