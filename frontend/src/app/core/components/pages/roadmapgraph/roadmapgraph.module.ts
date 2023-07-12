import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoadmapgraphComponent } from './roadmapgraph.component';
import { MindMapModule } from '../../shared/mind-map/mind-map.module';
import { MatIconModule } from '@angular/material/icon';
import {MatProgressBarModule} from '@angular/material/progress-bar';



@NgModule({
  declarations: [RoadmapgraphComponent],
  imports: [
    CommonModule,
    MindMapModule,
    MatIconModule,
    MatProgressBarModule,
  ],exports:[RoadmapgraphComponent]
})
export class RoadmapgraphModule { }
