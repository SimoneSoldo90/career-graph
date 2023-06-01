import { NgModule } from '@angular/core';
import { Route, RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './core/components/pages/login/login.component';
import { SkillsComponent } from './core/components/pages/skills/skills.component';
import { RoadmapsComponent } from './core/components/pages/roadmaps/roadmaps.component';
import { MenteesComponent } from './core/components/pages/mentees/mentees.component';
import { environment } from 'src/environment/environment';

export const menuroutes: any = [
  getRoute(environment.paths.skillspath,environment.paths.skills,SkillsComponent,null),
  getRoute(environment.paths.roadmapspath,environment.paths.roadmaps,RoadmapsComponent,null),
  getRoute(environment.paths.menteespath,environment.paths.mentees,MenteesComponent,null)
];

const routes: Routes = [
  getRoute("",environment.paths.roadmaps,RoadmapsComponent,null),
  getRoute(environment.paths.loginpath,environment.paths.login,LoginComponent,null),
  getRoute(environment.paths.skillspath,environment.paths.skills,SkillsComponent,null),
  getRoute(environment.paths.roadmapspath,environment.paths.roadmaps,RoadmapsComponent,null),
  getRoute(environment.paths.menteespath,environment.paths.mentees,MenteesComponent,null),
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

function getRoute(path: string, title: string, component: any, guard: any): Route {
  if ( guard !== null ) {
    return {
      path: path,
      title: title,
      component: component,
      canActivate: [guard]
    }
  } else {
    return {
      path: path,
      title: title,
      component: component,
    }
  }
}

