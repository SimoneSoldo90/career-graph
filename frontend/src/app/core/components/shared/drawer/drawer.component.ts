import { Component, Input, ViewChild, OnInit } from '@angular/core';
import { MatDrawer } from '@angular/material/sidenav';
import { Resource } from 'src/app/core/models/resource';
import { Resourcedetail, getKeys } from 'src/app/core/models/resourcedetail';
import { SkillService } from 'src/app/core/services/skill/skill.service';

@Component({
  selector: 'app-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['./drawer.component.css'],
})
export class DrawerComponent implements OnInit {
  @Input() currentState: any;
  @Input() allStates: any;
  @Input() showDropdown!: boolean;
  @Input() menuTitle!: string;
  @ViewChild(MatDrawer) drawer!: MatDrawer;

  skills!: any;
  resourceDetail: Resourcedetail = {
    LINK: [],
    LINK_VIDEO: [],
    NOTE: [],
  };

  ngOnInit(): void {
    const keysProp: (keyof Resourcedetail)[] = getKeys();
    this.skillService.currentMessage.subscribe((skills) => {
      this.skills = skills;
      if (this.drawer !== undefined) {
        this.skills.resources.forEach((resource: Resource) => {
          keysProp.filter((e) => {
            // La prossima linea di codice serve solo per riempire le descrizioni genericamente e visualizzarle a schermo
            // Più avanti sarà da eliminare perchè quando avremo dei dati veri ne sovrascriverà le descrizioni
            resource.description =
              "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
            if (e === resource.type) {
              this.resourceDetail[e]?.push(resource);
            }
          });
        });
        this.drawer.toggle();
        console.log(this.resourceDetail);
      }
    });
  }

  constructor(private skillService: SkillService) {}
  onClosedSideNav() {
    this.resourceDetail = this.resetSkill();
  }

  resetSkill(): Resourcedetail {
    let skillTmp: Resourcedetail = {
      LINK: [],
      LINK_VIDEO: [],
      NOTE: [],
    };
    return skillTmp;
  }
}
