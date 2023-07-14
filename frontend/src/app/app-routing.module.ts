import { NgModule } from '@angular/core';
import { Route, RouterModule, Routes } from '@angular/router';
import { SkillsComponent } from './core/components/pages/skills/skills.component';
import { RoadmapsComponent } from './core/components/pages/roadmaps/roadmaps.component';
import { RoadmapComponent } from './core/components/pages/roadmap/roadmap.component';
import { MenteesComponent } from './core/components/pages/mentees/mentees.component';
import { FormPageComponent } from './core/components/pages/form-page/form-page.component';
import { RoadmapgraphComponent } from './core/components/pages/roadmapgraph/roadmapgraph.component';

export const menuroutes: any = [
  getRoute("skills","Skills",SkillsComponent,null),
  getRoute("roadmaps","Roadmaps",RoadmapsComponent,null),
  getRoute("mentees","Mentees",MenteesComponent,null),
];

const routes: Routes = [
  getRoute("","Roadmaps",RoadmapsComponent,null),
  getRoute("skills","Skills",SkillsComponent,null),
  getRoute("roadmaps","Roadmaps",RoadmapsComponent,null),
  getRoute("roadmap","Roadmap",RoadmapComponent,null),
  getRoute("mentees","Mentees",MenteesComponent,null),
  getRoute("form","Form",FormPageComponent,null),
  getRoute("mindmap","Roadmap",RoadmapgraphComponent,null),
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

