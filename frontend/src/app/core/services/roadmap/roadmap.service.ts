import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Roadmap } from '../../models/roadmap';
import { Observable, map } from 'rxjs';
import { environment } from 'src/environment/environment';
import { RoadmapsModule } from '../../components/pages/roadmaps/roadmaps.module';

@Injectable({
  providedIn: 'any'
})
export class RoadmapService {

  baseUrl = environment.serverHost + "roadmaps/";

  constructor(private http:HttpClient) { }

  getRoadmap(id:number): Observable<any> {
    return this.http.get(this.baseUrl + "id");
  }
  getRoadmaps(): Observable<Roadmap[]> {
    return this.http.get<Roadmap[]>(this.baseUrl);
  }
}
