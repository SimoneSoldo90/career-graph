import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Skill } from '../models/skill';
import { environment } from 'src/environment/environment';
import { BehaviorSubject, Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'any'
})
export class SkillService {

  private messageSource = new BehaviorSubject('default message');
  currentMessage = this.messageSource.asObservable();
  baseUrl = environment.serverHost + "skills";

  constructor(private http: HttpClient) { }

  createSkill(skill: Skill): Observable<Skill> {
    return this.http.post<Skill>(this.baseUrl, skill);
  }

  getAllSkills(): Observable<Skill[]> {
    return this.http.get<Skill[]>(this.baseUrl);
  }

  getSkillById(skillId: number): Observable<Skill> {
    const url = `${this.baseUrl}/${skillId}`;
    return this.http.get<Skill>(url);
  }

  updateSkill(skillId: number, skill: Skill): Observable<Skill> {
    const url = `${this.baseUrl}/${skillId}`;
    return this.http.put<Skill>(url, skill);
  }

  deleteSkill(skillId: number): Observable<void> {
    const url = `${this.baseUrl}/${skillId}`;
    return this.http.delete<void>(url);
  }
  changeMessage(message: any) {
    this.messageSource.next(message)
  }
}
