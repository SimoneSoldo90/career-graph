import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Roadmap } from '../models/roadmap';
import { Observable, Subject, of } from 'rxjs';
import { environment } from 'src/environment/environment';
import { Skill } from '../models/skill';

@Injectable({
  providedIn: 'any'
})
export class RoadmapService {

  tmpSkills!: Skill[];

  baseUrl = environment.serverHost + "roadmaps/";

  constructor(private http:HttpClient) { }

  createRoadmap(roadmap: Roadmap): Observable<Roadmap> {
    return this.http.post<Roadmap>(this.baseUrl, roadmap);
  }

  getAllRoadmaps(): Observable<Roadmap[]> {
    return this.http.get<Roadmap[]>(this.baseUrl);
  }

  getRoadmapById(roadmapId: number): Observable<Roadmap> {
    const url = this.baseUrl+roadmapId;
    return this.http.get<Roadmap>(url);
  }

  updateRoadmap(roadmapId: number, roadmap: Roadmap): Observable<Roadmap> {
    const url = `${this.baseUrl}/${roadmapId}`;
    return this.http.put<Roadmap>(url, roadmap);
  }

  deleteRoadmap(roadmapId: number): Observable<void> {
    const url = `${this.baseUrl}${roadmapId}`;
    return this.http.delete<void>(url);
  }

}
