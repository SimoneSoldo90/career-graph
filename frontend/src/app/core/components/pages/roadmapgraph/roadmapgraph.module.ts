import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoadmapgraphComponent } from './roadmapgraph.component';
import { MindMapModule } from '../../shared/mind-map/mind-map.module';
import { MatIconModule } from '@angular/material/icon';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { BackbuttonModule } from '../../shared/backbutton/backbutton.module';



@NgModule({
  declarations: [RoadmapgraphComponent],
  imports: [
    CommonModule,
    MindMapModule,
    MatIconModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    BackbuttonModule,
  ],exports:[RoadmapgraphComponent]
})
export class RoadmapgraphModule { }
