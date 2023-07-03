import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Skill } from '../../models/skill';
import { environment } from 'src/environment/environment';
import { BehaviorSubject, Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'any'
})
export class SkillService {

  private messageSource = new BehaviorSubject('default message');
  currentMessage = this.messageSource.asObservable();
  baseUrl = environment.serverHost + "skills/";

  constructor(private http: HttpClient) { }

  getSkills(): Observable<Skill[]> {
    return this.http.get<Skill[]>(this.baseUrl);
  }
  getSkill(id:number): Observable<Skill> {
    return this.http.get<Skill>(this.baseUrl + id);
  }

  changeMessage(message: any) {
    this.messageSource.next(message)
  }
}
