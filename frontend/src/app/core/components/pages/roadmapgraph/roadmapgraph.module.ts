import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoadmapgraphComponent } from './roadmapgraph.component';
import { MindMapModule } from '../../shared/mind-map/mind-map.module';



@NgModule({
  declarations: [RoadmapgraphComponent],
  imports: [
    CommonModule,
    MindMapModule,
  ],exports:[RoadmapgraphComponent]
})
export class RoadmapgraphModule { }
