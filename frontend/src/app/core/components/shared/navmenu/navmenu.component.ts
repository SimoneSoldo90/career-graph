import { Component, QueryList, ViewChildren } from '@angular/core';
import { MatMenuTrigger } from '@angular/material/menu';
import { environment } from 'src/environment/environment';
import { Router } from '@angular/router';
import { menuroutes } from 'src/app/app-routing.module';
@Component({
  selector: 'app-navmenu',
  templateUrl: './navmenu.component.html',
  styleUrls: ['./navmenu.component.css'],
})
export class NavmenuComponent {

  @ViewChildren(MatMenuTrigger) trigger!: QueryList<MatMenuTrigger>;
  menuroutes=menuroutes;
  menuTitle:string='Menu'

  constructor(private router: Router) { }

  setMenuVoiceAndNavigate(itemname:string,itempath:string){
    this.menuTitle = itemname;
    this.router.navigate([itempath]);
  }

  skills:string=environment.paths.skills
  roadmaps:string=environment.paths.roadmaps
  skillspath:string=environment.paths.skillspath
  roadmapspath:string=environment.paths.roadmapspath
  mentees:string=environment.paths.mentees
  menteespath:string=environment.paths.menteespath
}
