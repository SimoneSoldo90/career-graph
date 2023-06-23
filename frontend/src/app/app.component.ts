import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDrawer } from '@angular/material/sidenav';

import { Skill } from './core/models/skill';
import { Resource } from './core/models/resource';
import { SkillService } from './core/services/skill/skill.service';
import { Resourcedetail, getKeys } from './core/models/resourcedetail';

import { keys } from 'ts-transformer-keys';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'career-graph';

  skills!: any;
  resourceDetail: Resourcedetail = {
    LINK: [],
    LINK_VIDEO: [],
    NOTE: []
  };

  @ViewChild(MatDrawer) drawer!: MatDrawer;

  constructor(private skillService: SkillService) {}

  ngOnInit(): void {
    localStorage.setItem('userFullName', 'Simone Soldo');
    const keysProp: (keyof Resourcedetail)[] = getKeys();
    this.skillService.currentMessage.subscribe((skills) => {
      this.skills = skills;
      if (this.drawer !== undefined) {
        this.skills.resources.forEach((resource: Resource) => {
          keysProp.filter(e => {
            // La prossima linea di codice serve solo per riempire le descrizioni genericamente e visualizzarle a schermo
            // Più avanti sarà da eliminare perchè quando avremo dei dati veri ne sovrascriverà le descrizioni
            resource.description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
            if(e === resource.type){
              this.resourceDetail[e]?.push(resource);
            }
          })
        });
        this.drawer.toggle();
        console.log(this.resourceDetail)
      }
    });
  }

  onClosedSideNav(){
    this.resourceDetail = this.resetSkill();
  }

  resetSkill(): Resourcedetail {
    let skillTmp: Resourcedetail = {
      LINK: [],
      LINK_VIDEO: [],
      NOTE: []
    };
    return skillTmp;
  }
}
