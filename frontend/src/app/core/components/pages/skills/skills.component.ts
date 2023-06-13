import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Skill } from 'src/app/core/models/skill';
import { SkillService } from 'src/app/core/services/skill/skill.service';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.css']
})
export class SkillsComponent implements OnInit {

  dataSource: Skill[] = [];
  displayedColumns = ["id", "title"];
  tableOptions = {
    "type": "skills",
    "displayedColumns": this.displayedColumns,
    "canDelete": true,
    "canModify": true,
    btnCreate:{
      "canCreate":  true,
      "canView": true
    }
  };
  constructor(private skillService: SkillService, private router: Router) {}

  ngOnInit(): void {
    this.getSkills();
    // can delete e can modify da modificare in base al ruolo!!
  }

  getSkills(): void {
    this.skillService.getSkills().subscribe( {
      next: (data: Skill[]) => {
        this.dataSource = data;
    }});
  }

  createNewSkill(event: boolean){
    if(event){
      this.router.navigate(['/form', { createMode: true, type: "skill" }]);
    }
  }

  getSkill(skillId: number): void {
    this.skillService.getSkill(skillId).subscribe( {
      next: (data: Skill) => {
        console.log(data)
      }
    })
  }

  visualizeSkills(event: Skill){
    console.log(event);
  }
}
