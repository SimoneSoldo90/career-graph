import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'career-graph';
  statoCorrente = 'To Do';
  states = {
    allStates: [
      {
          title: 'To Do',
          statusColor: 'white',
          color:'black'
      },
      {
          title: 'Doing',
          statusColor: '#74b9ff',
          color:'black'
      },
      {
          title: 'Done',
          statusColor: '#00b894',
          color:'black'
      },
    ],
  };
  titleMenuDrawer = 'Aggiorna Stato';
  constructor() {}

  ngOnInit(): void {
    localStorage.setItem('userFullName', 'Simone Soldo');
  }
}
