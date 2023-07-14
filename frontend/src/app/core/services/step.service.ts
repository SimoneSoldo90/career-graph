import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';
import { Step } from '../models/step.model';

@Injectable({
  providedIn: 'root'
})
export class StepService {
  private  baseUrl = environment.serverHost + '/api/steps'; // Modifica l'URL di base in base alla tua configurazione del server API

  constructor(private http: HttpClient) { }

  // GET /steps/
  getAllSteps(): Observable<Step[]> {
    return this.http.get<Step[]>(this.baseUrl);
  }

  // POST /steps/
  createStep(step: Step): Observable<Step> {
    return this.http.post<Step>(this.baseUrl, step);
  }

  // GET /steps/{stepId}
  getStepById(stepId: number): Observable<Step> {
    const url = `${this.baseUrl}/${stepId}`;
    return this.http.get<Step>(url);
  }

  // PUT /steps/{stepId}
  updateStep(stepId: number, step: Step): Observable<Step> {
    const url = `${this.baseUrl}/${stepId}`;
    return this.http.put<Step>(url, step);
  }

  // DELETE /steps/{stepId}
  deleteStep(stepId: number): Observable<void> {
    const url = `${this.baseUrl}/${stepId}`;
    return this.http.delete<void>(url);
  }

  // POST /steps/{stepId}/roadmap-links/
  createRoadmapLink(stepId: number, roadmapLink: any): Observable<any> {
    const url = `${this.baseUrl}/${stepId}/roadmap-links/`;
    return this.http.post<any>(url, roadmapLink);
  }

  // PUT /steps/{stepId}/roadmap-links/{roadmapLinkId}
  updateRoadmapLink(stepId: number, roadmapLinkId: number, roadmapLink: any): Observable<any> {
    const url = `${this.baseUrl}/${stepId}/roadmap-links/${roadmapLinkId}`;
    return this.http.put<any>(url, roadmapLink);
  }

  // DELETE /steps/{stepId}/roadmap-links/{roadmapLinkId}
  deleteRoadmapLink(stepId: number, roadmapLinkId: number): Observable<void> {
    const url = `${this.baseUrl}/${stepId}/roadmap-links/${roadmapLinkId}`;
    return this.http.delete<void>(url);
  }

  // POST /step/{stepId}/resource
  createResource(stepId: number, resource: any): Observable<any> {
    const url = `${this.baseUrl}/${stepId}/resource`;
    return this.http.post<any>(url, resource);
  }

  // PUT /step/{stepId}/resource/{resourceId}
  updateResource(stepId: number, resourceId: number, resource: any): Observable<any> {
    const url = `${this.baseUrl}/${stepId}/resource/${resourceId}`;
    return this.http.put<any>(url, resource);
  }

  // DELETE /step/{stepId}/resource/{resourceId}
  deleteResource(stepId: number, resourceId: number): Observable<void> {
    const url = `${this.baseUrl}/${stepId}/resource/${resourceId}`;
    return this.http.delete<void>(url);
  }
}
