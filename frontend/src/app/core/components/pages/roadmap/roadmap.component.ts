import { Component, OnInit, ViewChild } from '@angular/core';

import { Skill } from 'src/app/core/models/skill';
import { RoadmapService } from 'src/app/core/services/roadmap/roadmap.service';
import { GenericTableComponent } from '../../shared/generic-table/generic-table.component';
import { SkillService } from 'src/app/core/services/skill/skill.service';
import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-roadmap',
  templateUrl: './roadmap.component.html',
  styleUrls: ['./roadmap.component.css']
})
export class RoadmapComponent implements OnInit {
  @ViewChild("genericTable") genericTable!: GenericTableComponent;
  public id!: number;
  title = 'Roadmap ';
  detailTitle = window.history.state.options.elementTitle;
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
    "detailTitle": this.detailTitle,
    "emptyData": false,
    btnVisualize:{
      canView:true,
      tooltip:"Visualizza grafo",
      routerLink:"/roadmapgraph",
      queryParams:{id:1}
    }
  };

  constructor(private roadmapService: RoadmapService, private skillService: SkillService,private router: Router) {
    console.log(this.id)
    }

  ngOnInit() {
    this.id = window.history.state.options.elementId;
    this.getRoadmapSkills();
    this.getAllSkills();
  }

  getRoadmapSkills(): void {
    this.roadmapService.getRoadmap(this.id).subscribe( {
      next: (data: Skill[]) => {
        data.forEach((parentSkill: any) => {
          this.tmpData.push(parentSkill);
        });
        this.dataSource = this.tmpData;

      },
      error: (error: HttpErrorResponse) => {
        if(error.status === HttpStatusCode.NotFound){
          this.tableOptions.emptyData = true;
        } else {
          //console.log(error.message)
        }
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

  visualizeSkills(event: Skill){
    this.getSkill(Number(event.id));
  }

  getSkill(skillId: number): void {
    this.skillService.getSkill(skillId).subscribe( {
      next: (data: Skill) => {
        let dataToPass = data;
        this.skillService.changeMessage(dataToPass);
      }
    })
  }
}
