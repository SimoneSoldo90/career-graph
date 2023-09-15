import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoadmapgraphComponent } from './roadmapgraph.component';
import { MindMapModule } from '../../shared/mind-map/mind-map.module';
import { MatIconModule } from '@angular/material/icon';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { BackbuttonModule } from '../../shared/backbutton/backbutton.module';
import { MatChipsModule } from '@angular/material/chips';



@NgModule({
  declarations: [RoadmapgraphComponent],
  imports: [
    CommonModule,
    MindMapModule,
    MatIconModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    BackbuttonModule,
    MatChipsModule,
  ],exports:[RoadmapgraphComponent]
})
export class RoadmapgraphModule { }
