import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserSkill } from '../models/user-skill.model';
import { environment } from 'src/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class UserSkillService {
  private baseUrl = environment.serverHost +  '/api/users';

  constructor(private http: HttpClient) { }

  createUserSkill(userId: number, userSkill: UserSkill): Observable<UserSkill> {
    const url = `${this.baseUrl}/${userId}/user-skills`;
    return this.http.post<UserSkill>(url, userSkill);
  }

  getUserSkillsByUserId(userId: number): Observable<UserSkill[]> {
    const url = `${this.baseUrl}/${userId}/user-skills`;
    return this.http.get<UserSkill[]>(url);
  }

  getUserSkillById(userId: number, userSkillId: number): Observable<UserSkill> {
    const url = `${this.baseUrl}/${userId}/user-skills/${userSkillId}`;
    return this.http.get<UserSkill>(url);
  }

  updateUserSkill(userId: number, userSkillId: number, userSkill: UserSkill): Observable<UserSkill> {
    const url = `${this.baseUrl}/${userId}/user-skills/${userSkillId}`;
    return this.http.put<UserSkill>(url, userSkill);
  }

  deleteUserSkill(userId: number, userSkillId: number): Observable<void> {
    const url = `${this.baseUrl}/${userId}/user-skills/${userSkillId}`;
    return this.http.delete<void>(url);
  }
}
