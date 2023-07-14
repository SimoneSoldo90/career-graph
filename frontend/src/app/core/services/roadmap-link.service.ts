import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RoadmapLink } from '../models/roadmap-link.model';
import { environment } from 'src/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class RoadmapLinkService {
  private  baseUrl = environment.serverHost + '/api/steps';

  constructor(private http: HttpClient) { }

  createRoadmapLink(stepId: number, roadmapLink: RoadmapLink): Observable<RoadmapLink> {
    const url = `${this.baseUrl}/${stepId}/roadmap-links`;
    return this.http.post<RoadmapLink>(url, roadmapLink);
  }

  getRoadmapLinksByStepId(stepId: number): Observable<RoadmapLink[]> {
    const url = `${this.baseUrl}/${stepId}/roadmap-links`;
    return this.http.get<RoadmapLink[]>(url);
  }

  getRoadmapLinkById(stepId: number, roadmapLinkId: number): Observable<RoadmapLink> {
    const url = `${this.baseUrl}/${stepId}/roadmap-links/${roadmapLinkId}`;
    return this.http.get<RoadmapLink>(url);
  }

  updateRoadmapLink(stepId: number, roadmapLinkId: number, roadmapLink: RoadmapLink): Observable<RoadmapLink> {
    const url = `${this.baseUrl}/${stepId}/roadmap-links/${roadmapLinkId}`;
    return this.http.put<RoadmapLink>(url, roadmapLink);
  }

  deleteRoadmapLink(stepId: number, roadmapLinkId: number): Observable<void> {
    const url = `${this.baseUrl}/${stepId}/roadmap-links/${roadmapLinkId}`;
    return this.http.delete<void>(url);
  }
}
