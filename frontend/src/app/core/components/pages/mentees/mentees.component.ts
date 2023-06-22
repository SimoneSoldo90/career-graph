import { Component, OnInit } from '@angular/core';

import { Mentee } from 'src/app/core/models/mentee';
import { MenteeService } from 'src/app/core/services/mentee/mentee.service';

@Component({
  selector: 'app-mentees',
  templateUrl: './mentees.component.html',
  styleUrls: ['./mentees.component.css']
})
export class MenteesComponent implements OnInit {

  title = 'Mentee';
  dataSource: Mentee[] = [];
  displayedColumns = ["id", "firstName", "lastName", "email"];
  tableDef: Array<any> = [
    {
      key: 'id',
      header: 'ID',
    },    {
      key: 'firstName',
      header: 'Nome',
    },    {
      key: 'lastName',
      header: 'Cognome',
    },    {
      key: 'email',
      header: 'Email',
    },
  ]
  tableOptions = {
    "type": "mentee",
    "displayedColumns": this.displayedColumns,
    "tableDef": this.tableDef,
    "canDelete": false,
    "canModify": false,
    btnCreate:{
      "canCreate":  false,
      "canView": true
    },
    "title": this.title,
  };

  constructor(private menteeService: MenteeService) { }

  ngOnInit(): void {
    this.getMentees();
  }

  getMentees(): void {
    this.menteeService.getMentees().subscribe( {
      next: (data: Mentee[]) => {
        this.dataSource = data;
      }
    })
  }
}
