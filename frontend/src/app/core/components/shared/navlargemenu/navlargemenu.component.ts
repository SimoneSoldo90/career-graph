import { Component, QueryList, ViewChildren } from '@angular/core';
import { MatMenuTrigger } from '@angular/material/menu';
import { environment } from 'src/environment/environment';
import { Router } from '@angular/router';
import { menuroutes } from 'src/app/app-routing.module';

@Component({
  selector: 'app-navlargemenu',
  templateUrl: './navlargemenu.component.html',
  styleUrls: ['./navlargemenu.component.css']
})
export class NavlargemenuComponent {

  @ViewChildren(MatMenuTrigger) trigger!: QueryList<MatMenuTrigger>;
  menuroutes=menuroutes;
  menuTitle:string='Menu'

  constructor(private router: Router) { }

  setMenuVoiceAndNavigate(itemname:string,itempath:string){
    this.menuTitle = itemname;
    this.router.navigate([itempath]);
  }

  skills:string="Skills"
  roadmaps:string="Roadmaps"
  skillspath:string="skills"
  roadmapspath:string="roadmaps"
  mentees:string="Mentees"
  menteespath:string="mentees"
}
