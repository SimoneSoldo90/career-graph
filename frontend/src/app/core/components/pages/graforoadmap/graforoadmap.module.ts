import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GraforoadmapComponent } from './graforoadmap.component';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { RoadmaptreeModule } from '../../shared/roadmaptree/roadmaptree.module';



@NgModule({
  declarations: [
    GraforoadmapComponent
  ],
  imports: [
    CommonModule,
    MatIconModule,
    MatProgressBarModule,
    RoadmaptreeModule,
  ],exports:[GraforoadmapComponent]
})
export class GraforoadmapModule { }
