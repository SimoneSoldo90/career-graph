import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Skill } from '../../models/skill';
import { SkillsModule } from '../../components/pages/skills/skills.module';
import { Observable, map } from 'rxjs';
import { environment } from 'src/environment/environment';

@Injectable({
  providedIn: 'any'
})
export class SkillService {

  baseUrl = environment.serverHost + "skills/";

  constructor(private http: HttpClient) { }

  getSkills(): Observable<Skill[]> {
    return this.http.get<Skill[]>(this.baseUrl);
  }
}
