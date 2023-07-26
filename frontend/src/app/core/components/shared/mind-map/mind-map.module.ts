import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MindMapComponent } from './mind-map.component';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { RouterModule } from '@angular/router';
import { MatBadgeModule } from '@angular/material/badge';
import { MatTooltipModule } from '@angular/material/tooltip';



@NgModule({
  declarations: [
    MindMapComponent
  ],
  imports: [
    CommonModule,
    MatGridListModule,
    MatSlideToggleModule,
    RouterModule,
    MatBadgeModule,
    MatTooltipModule,
  ],
  exports: [
    MindMapComponent,
  ],
})
export class MindMapModule { }
