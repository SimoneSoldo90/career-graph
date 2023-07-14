import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Resource } from '../models/resource.model';
import { environment } from 'src/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class ResourceService {
  private  baseUrl = environment.serverHost + '/api/step';

  constructor(private http: HttpClient) { }

  createResource(stepId: number, resource: Resource): Observable<Resource> {
    const url = `${this.baseUrl}/${stepId}/resource`;
    return this.http.post<Resource>(url, resource);
  }

  getResourcesByStepId(stepId: number): Observable<Resource[]> {
    const url = `${this.baseUrl}/${stepId}/resource`;
    return this.http.get<Resource[]>(url);
  }

  getResourceById(stepId: number, resourceId: number): Observable<Resource> {
    const url = `${this.baseUrl}/${stepId}/resource/${resourceId}`;
    return this.http.get<Resource>(url);
  }

  updateResource(stepId: number, resourceId: number, resource: Resource): Observable<Resource> {
    const url = `${this.baseUrl}/${stepId}/resource/${resourceId}`;
    return this.http.put<Resource>(url, resource);
  }

  deleteResource(stepId: number, resourceId: number): Observable<void> {
    const url = `${this.baseUrl}/${stepId}/resource/${resourceId}`;
    return this.http.delete<void>(url);
  }
}
