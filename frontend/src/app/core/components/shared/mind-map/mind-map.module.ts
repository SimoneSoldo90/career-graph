import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MindMapComponent } from './mind-map.component';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    MindMapComponent
  ],
  imports: [
    CommonModule,
    MatGridListModule,
    MatSlideToggleModule,
    RouterModule
  ],
  exports: [
    MindMapComponent,
  ],
})
export class MindMapModule { }
