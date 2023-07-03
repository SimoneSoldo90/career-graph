import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from 'src/environment/environment';
import { Observable, of } from 'rxjs';
import { Mentee } from '../../models/mentee';

@Injectable({
  providedIn: 'root'
})
export class MenteeService {

  baseUrl = environment.serverHost + "users";

  constructor(private http: HttpClient) { }

  getMentees(): Observable<Mentee[]> {
    return this.http.get<Mentee[]>(this.baseUrl);
  }
  // getMentee(id:number): Observable<any> {
  //   return this.http.get<Mentee>(this.baseUrl + id);
  // }

  // getMenteesFromMentor(id: number): Observable<Mentee[]> {
  //   return this.http.get<Mentee[]>(environment.serverHost + 'mentor/' + id + '/mentees/');
  // }
}
