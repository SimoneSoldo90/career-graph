import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Resource } from '../../models/resource';
import { Observable, map } from 'rxjs';
import { environment } from 'src/environment/environment';
import { Skill } from '../../models/skill';


@Injectable({
  providedIn: 'any'
})
export class ResourceService {

  baseUrl = environment.serverHost + "resources/";

  constructor(private http: HttpClient) { }

  getResources(): Observable<Resource[]> {
    return this.http.get<Resource[]>(this.baseUrl);
  }

}
