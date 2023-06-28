import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoadmapgraphComponent } from './roadmapgraph.component';
import { GraphNgxModule } from '../../shared/graph-ngx/graph-ngx.module';



@NgModule({
  declarations: [RoadmapgraphComponent],
  imports: [
    CommonModule,
    GraphNgxModule,
  ],exports:[RoadmapgraphComponent]
})
export class RoadmapgraphModule { }
