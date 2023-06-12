import { Component, OnInit } from '@angular/core';

import { Roadmap } from 'src/app/core/models/roadmap';
import { RoadmapService } from 'src/app/core/services/roadmap/roadmap.service';

@Component({
  selector: 'app-roadmaps',
  templateUrl: './roadmaps.component.html',
  styleUrls: ['./roadmaps.component.css']
})
export class RoadmapsComponent implements OnInit {

  dataSource: Roadmap[] = [];
  // displayedColumns: string[] = [];
  displayedColumns = ["title"];
  tableOptions = {
    "displayedColumns": this.displayedColumns,
    "canDelete": true,
    "canModify": true,
  };
  constructor(private roadmapService: RoadmapService) {}

  ngOnInit(): void {
    this.getRoadmaps();
    // can delete e can modify da modificare in base al ruolo!!
  }

  getRoadmaps(): void {
    this.roadmapService.getRoadmaps().subscribe( {
      next: (data: Roadmap[]) => {
        this.dataSource = data;
      }
      // this.getType();
    });
  }

  // getType(): void{
  //   this.displayedColumns = Object.keys(this.dataSource[0]);
  //   console.log(this.displayedColumns);
  // }
}
