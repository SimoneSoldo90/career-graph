import { Component, OnInit, ViewChild } from '@angular/core';

import { Skill } from 'src/app/core/models/skill';
import { RoadmapService } from 'src/app/core/services/roadmap.service';
import { GenericTableComponent } from '../../shared/generic-table/generic-table.component';
import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Router } from '@angular/router';
import { Roadmap } from 'src/app/core/models/roadmap';
import { Step } from 'src/app/core/models/step.model';
import { StepService } from 'src/app/core/services/step.service';
import { SkillService } from 'src/app/core/services/skill.service';

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
  tmpData: Step[] = [];

  dataSource: Step[] = [];
  displayedColumns = ["id", "title"];
  totalStep: Step[] = [];
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
    "type": "roadmapSteps",
    "displayedColumns": this.displayedColumns,
    "tableDef": this.tableDef,
    "canDelete": true,
    "canModify": true,
    btnCreate:{
      "type":"menu",
      "title":"Associa Skill",
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
      routerLink:"/mindmap",
      queryParams:{id:this.id}
    }
  };

  constructor(private roadmapService: RoadmapService, private stepService: StepService,private skillService:SkillService,private router: Router) {

  }

  ngOnInit() {
    this.id = window.history.state.options.elementId;
    this.getRoadmap();
  }

  getRoadmap(): void {
    this.roadmapService.getRoadmapById(this.id).subscribe( {
      next: (data: Roadmap) => {
        this.tmpData.push(...data.steps!);
        this.dataSource = this.tmpData;
      },
      error: (error: HttpErrorResponse) => {
        if(error.status === HttpStatusCode.NotFound){
          this.tableOptions.emptyData = true;
        } else {

        }
      }
    });
  }

  getAllSteps(): void {
   this.stepService.getAllSteps().subscribe({
    next:(data:Step[])=>{
      this.totalStep.push(...data)
    }
   })
  }

  updateRoadmapStep(data: Step): void {
    if(!this.dataSource.filter(element=>element.id === data.id)){

      this.dataSource.push(data);
      this.genericTable.setUpDataInput(this.dataSource);
      this.genericTable.setUpDataMenuButton(this.totalStep);
    }
  }

  visualizeSkills(event: Skill){
    this.getSkillById(Number(event.id));
  }

  getSkillById(skillId: number): void {
    this.skillService.getSkillById(skillId).subscribe( {
      next: (data: Skill) => {
        let dataToPass = data;
        this.skillService.changeMessage(dataToPass);
      }
    })
  }
}
