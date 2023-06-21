import { Component, OnInit } from '@angular/core';

import { Skill } from 'src/app/core/models/skill';
import { RoadmapService } from 'src/app/core/services/roadmap/roadmap.service';

@Component({
  selector: 'app-roadmap',
  templateUrl: './roadmap.component.html',
  styleUrls: ['./roadmap.component.css']
})
export class RoadmapComponent implements OnInit {
  public id!: number;

  dataSource: Skill[] = [];
  displayedColumns = ["id", "title"];
  totalSkills: Skill[] = [];
  tableDef: Array<any> = [
    {
      key: 'id',
      header: 'ID',
    },    {
      key: 'title',
      header: 'Title',
    },
  ]
  tableOptions = {
    "type": "roadmapSkills",
    "displayedColumns": this.displayedColumns,
    "tableDef": this.tableDef,
    "canDelete": true,
    "canModify": true,
    btnCreate:{
      "canCreate":  true,
      "canView": true
    }
  };

  constructor(private roadmapService: RoadmapService) { }

  ngOnInit() {
    this.id = window.history.state.options;
    this.getRoadmapSkills();
    this.getAllSkills();
  }

  getRoadmapSkills(): void {
    this.roadmapService.getRoadmap(this.id).subscribe( {
      next: (data: Skill[]) => {
        this.dataSource = data;
      }
    });
  }

  getAllSkills(): void {
    this.roadmapService.getAllSkills().subscribe( {
      next: (data: Skill[]) => {
        this.totalSkills = data;
      }
    })
  }

}
