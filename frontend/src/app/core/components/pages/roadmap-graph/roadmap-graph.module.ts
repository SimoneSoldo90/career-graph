import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoadmapGraphComponent } from './roadmap-graph.component';
import { NgxGraphModule } from '@swimlane/ngx-graph';



@NgModule({
  declarations: [RoadmapGraphComponent],
  imports: [
    CommonModule,
    NgxGraphModule,
  ],
  exports: [RoadmapGraphComponent]
})
export class RoadmapGraphModule { }
