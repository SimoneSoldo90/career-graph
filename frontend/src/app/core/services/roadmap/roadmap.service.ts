import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Roadmap } from '../../models/roadmap';
import { Observable, Subject, of } from 'rxjs';
import { environment } from 'src/environment/environment';
import { RoadmapsModule } from '../../components/pages/roadmaps/roadmaps.module';
import { Skill } from '../../models/skill';

@Injectable({
  providedIn: 'any'
})
export class RoadmapService {

  tmpSkills!: Skill[];

  serverHostUrl = environment.serverHost;
  baseUrl = this.serverHostUrl + "roadmaps/";

  constructor(private http:HttpClient) { }

  getRoadmap(id:number): Observable<any> {
    return this.http.get(this.baseUrl + id + "/roadmapskills");
  }
  getRoadmaps(): Observable<Roadmap[]> {
    return this.http.get<Roadmap[]>(this.baseUrl);
  }
  getAllSkills(): Observable<Skill[]> {
    return this.http.get<Skill[]>(this.serverHostUrl + "skills/");
  }

}
