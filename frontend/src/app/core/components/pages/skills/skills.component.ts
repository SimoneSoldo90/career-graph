import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Skill } from 'src/app/core/models/skill';
import { Resource } from 'src/app/core/models/resource';
import { SkillService } from 'src/app/core/services/skill/skill.service';
import { ResourceService } from 'src/app/core/services/resource/resource.service';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.css']
})
export class SkillsComponent implements OnInit {

  skillDataSource: Skill[] = [];
  resourceDataSource: Resource[] = [];

  constructor(private resourceService: ResourceService, private skillService: SkillService, private router: Router) {}

   ngOnInit(): void {
    this.getResources();
    this.getSkills();
   }

  getResources(): void {
    this.resourceService.getResources().subscribe( {
      next: (data: Resource[]) => {
        this.resourceDataSource = data;
    }});
    console.log(this.resourceDataSource);
  }

  getSkills(): void {
    this.skillService.getSkills().subscribe( {
      next: (data: Skill[]) => {
        this.skillDataSource = data;
    }});
  }

  createNewSkill(event: boolean){
    if(event){
      this.router.navigate(['/form', { createMode: true, type: "skill", referenced: false }]);
    }
  }

}
