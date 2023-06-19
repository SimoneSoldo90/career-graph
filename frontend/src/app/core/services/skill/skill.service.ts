import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Skill } from '../../models/skill';
import { environment } from 'src/environment/environment';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'any'
})
export class SkillService {

  baseUrl = environment.serverHost + "skills/";

  private messageSource = new BehaviorSubject('default message');
  currentMessage = this.messageSource.asObservable();

  constructor(private http: HttpClient) { }

  getSkill(id:number): Observable<any> {
    return this.http.get<Skill>(this.baseUrl + id);
  }
  getSkills(): Observable<Skill[]> {
    return this.http.get<Skill[]>(this.baseUrl);
  }


  changeMessage(message: any) {
    this.messageSource.next(message)
  }
}
