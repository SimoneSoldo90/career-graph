import { Component, OnInit } from '@angular/core';

import { Mentee } from 'src/app/core/models/mentee';
import { MenteeService } from 'src/app/core/services/mentee/mentee.service';

@Component({
  selector: 'app-mentees',
  templateUrl: './mentees.component.html',
  styleUrls: ['./mentees.component.css']
})
export class MenteesComponent implements OnInit {

  dataSource: Mentee[] = [];
  displayedColumns = ["id", "first_name"];
  tableOptions = {
    "type": "mentee",
    "displayedColumns": this.displayedColumns,
    "canDelete": false,
    "canModify": false,
    btnCreate:{
      "canCreate":  false,
      "canView": true
    }
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
