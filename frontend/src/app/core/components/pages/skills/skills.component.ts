import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import { Skill } from 'src/app/core/models/skill';
import { Resource } from 'src/app/core/models/resource.model';
import { SkillService } from 'src/app/core/services/skill.service';
import { ResourceService } from 'src/app/core/services/resource.service';


@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.css']
})
export class SkillsComponent implements OnInit {

  title = 'Skills'
  detailTitle = '';
  dataSource: Skill[] = [];
  resourceDataSource: Resource[] = [];
  displayedColumns = ["id", "title"];
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
    "type": "skill",
    "displayedColumns": this.displayedColumns,
    "tableDef": this.tableDef,
    "canDelete": true,
    "canModify": true,
    btnCreate:{
      "title":"Aggiungi Skill",
      "canCreate":  true,
      "canView": true
    },
    "title": this.title,
    "detailTitle": this.detailTitle,
    "emptyData": false,
  };
  @Output() skillToView = new EventEmitter<any>();

  constructor(private resourceService: ResourceService, private skillService: SkillService, private router: Router) {}

   ngOnInit(): void {
    this.getResources();
    this.getSkills();
   }

  getResources(): void {
    this.resourceService.getResourcesByStepId(1).subscribe( {
      next: (data: Resource[]) => {
        this.resourceDataSource = data;
    }});
    console.log(this.resourceDataSource);
  }

  getSkills(): void {
    this.skillService.getAllSkills().subscribe( {
      next: (data: Skill[]) => {
        this.dataSource = data;
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

  createNewSkill(event: boolean){
    if(event){
      this.router.navigate(['/form', { createMode: true, type: "skill", referenced: false }]);
    }
  }

  visualizeSkills(event: Skill){
    this.getSkillById(Number(event.id));
  }

  getSkillById(skillId: number): void {
    this.skillService.getSkillById(skillId).subscribe( {
      next: (data: Skill) => {
        this.skillService.changeMessage(data);
      }
    })
  }
}
