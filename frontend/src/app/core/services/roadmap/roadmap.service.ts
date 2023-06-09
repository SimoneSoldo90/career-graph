import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from 'src/environment/environment';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoadmapService {

  baseUrl = environment.serverHost + "roadmaps/";

  constructor(private http:HttpClient) { }

  getRoadmap(id:number): Observable<any> {
    return this.http.get(this.baseUrl + "id");
  }
}
