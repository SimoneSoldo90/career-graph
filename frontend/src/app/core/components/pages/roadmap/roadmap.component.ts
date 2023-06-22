import { Component, OnInit, ViewChild } from '@angular/core';

import { Skill } from 'src/app/core/models/skill';
import { RoadmapService } from 'src/app/core/services/roadmap/roadmap.service';
import { GenericTableComponent } from '../../shared/generic-table/generic-table.component';

@Component({
  selector: 'app-roadmap',
  templateUrl: './roadmap.component.html',
  styleUrls: ['./roadmap.component.css']
})
export class RoadmapComponent implements OnInit {
  @ViewChild("genericTable") genericTable!: GenericTableComponent;
  public id!: number;
  title = 'Roadmap ' + window.history.state.options.elementTitle;
  tmp: any[] = [];
  tmpData: any[] = [];

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
    },
    "idRoadmap": Number(this.id),
    "title": this.title,
  };

  constructor(private roadmapService: RoadmapService) { }

  ngOnInit() {
    this.id = window.history.state.options.elementId;
    // this.title = 'ciao'
    let x = window.history.state.options.elementTitle;
    console.log(x)
    console.log(this.title)
    this.getRoadmapSkills();
    this.getAllSkills();
  }

  getRoadmapSkills(): void {
    this.roadmapService.getRoadmap(this.id).subscribe( {
      next: (data: Skill[]) => {
        data.forEach((parentSkill: any) => {
          this.tmpData.push(parentSkill.skill);
        });
        this.dataSource = this.tmpData;
      }
    });
  }

  getAllSkills(): void {
    this.totalSkills = [];
    this.roadmapService.getAllSkills().subscribe( {
      next: (data: Skill[]) => {
        // push di una skill di prova in assenza del backend
        // data.push({id: 100, title: '1 - New skill', description: 'speriamo che funzioni', enabled: true});
        this.totalSkills = data;
      }
    })
  }

  updateRoadmapSkill(data: Skill): void {
    this.dataSource.push(data);
    this.genericTable.setUpDataInput(this.dataSource);
    this.genericTable.setUpDataMenuButton(this.totalSkills);
  }
}
