import { Component } from '@angular/core';
import { Roadmap } from 'src/app/core/models/roadmap';
import { RoadmapService } from 'src/app/core/services/roadmap/roadmap.service';

@Component({
  selector: 'app-roadmaps',
  templateUrl: './roadmaps.component.html',
  styleUrls: ['./roadmaps.component.css']
})
export class RoadmapsComponent {

  roadmaps!: Roadmap[];

  ngOnInit(): void {

    //this.getRoadmapData();
  }

  // getRoadmapData(){
  //   this.RoadmapService.getRoadmap().subscribe({
  //     next: (roadmap: Roadmap) => {
  //       this.roadmaps = roadmap;
  //   }})
  // }

}
