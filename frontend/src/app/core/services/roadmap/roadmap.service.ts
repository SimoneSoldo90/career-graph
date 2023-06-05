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
  // roadMaps!: Roadmap[];

  constructor(private http: HttpClient) { }

  getRoadmap(): Observable<Roadmap[]> {
    return this.http.get<Roadmap[]>(this.baseUrl);
  }
}
