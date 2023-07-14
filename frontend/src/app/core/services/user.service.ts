import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { environment } from 'src/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private   baseUrl = environment.serverHost + '/api/users';

  constructor(private http: HttpClient) { }

  getUserById(userId: number): Observable<User> {
    const url = `${this.baseUrl}/${userId}`;
    return this.http.get<User>(url);
  }
}
