import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from 'src/environment/environment';
import { Observable, of } from 'rxjs';
import { Mentee } from '../../models/mentee';

const MENTEES_DATA: Mentee[] = [
  {id: 1, first_name: 'Carlo', last_name: 'Carli', email: 'carli@mail.com'},
  {id: 2, first_name: 'Santina', last_name: 'Annunziata', email: 'sannunzita@mail.com'},
  {id: 3, first_name: 'Claudio', last_name: 'Lotito', email: 'lozio@mail.com'},
  {id: 4, first_name: 'Ernesta', last_name: 'Ghevara', email: 'astalavictoria@mail.com'},
  {id: 5, first_name: 'Sasha', last_name: 'Coen', email: 'baron.coen@mail.com'},
  {id: 6, first_name: 'Alda', last_name: 'Merini', email: 'alda.merini@mail.com'},
  {id: 7, first_name: 'Gino', last_name: 'Strada', email: 'luis.street@mail.com'},
  {id: 8, first_name: 'Olmo', last_name: 'Abete', email: 'semper.verdis@mail.com'},
  {id: 9, first_name: 'Filomena', last_name: 'Carrino', email: 'filocarrino@mail.com'},
  {id: 10, first_name: 'Giorgio', last_name: 'Patelli', email: 'patorgio@mail.com'},
  {id: 11, first_name: 'Teodoro', last_name: 'Fonzi', email: 'fonzi@mail.com'},
  {id: 12, first_name: 'Cristina', last_name: 'Cerchiata', email: 'cerchiata@mail.com'},
  {id: 13, first_name: 'Elia', last_name: 'Spazzi', email: 'elia.spazzi@mail.com'},
  {id: 14, first_name: 'Saverio', last_name: 'Setola', email: 's.setola@mail.com'},
  {id: 15, first_name: 'Federica', last_name: 'Frigerio', email: 'fede.frigi@mail.com'},
  {id: 16, first_name: 'Gianna', last_name: 'Cechi', email: 'gia.cechi@mail.com'},
  {id: 17, first_name: 'Maria', last_name: 'Vetusta', email: 'mary.vet@mail.com'},
  {id: 18, first_name: 'Ciro', last_name: 'Immobile', email: 'ciro.immobilius@mail.com'},
  {id: 19, first_name: 'Achille', last_name: 'Lauro', email: 'achille.lauro@mail.com'},
  {id: 20, first_name: 'Ennio', last_name: 'Morricone', email: 'theking@mail.com'},
];
@Injectable({
  providedIn: 'root'
})
export class MenteeService {

  baseUrl = environment.serverHost + "mentees/";

  constructor(private http: HttpClient) { }

  getMentees(): Observable<Mentee[]> {
      return of(MENTEES_DATA);
    }

  // getMentee(id:number): Observable<any> {
  //   return this.http.get<Mentee>(this.baseUrl + id);
  // }
  // getMentees(): Observable<Mentee[]> {
  //   return this.http.get<Mentee[]>(this.baseUrl);
  // }

  // getMenteesFromMentor(id: number): Observable<Mentee[]> {
  //   return this.http.get<Mentee[]>(environment.serverHost + 'mentor/' + id + '/mentees/');
  // }
}
