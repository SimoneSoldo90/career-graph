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


}
