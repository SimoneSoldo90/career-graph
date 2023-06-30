import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'career-graph';
  allStates = ['Sato 1','Stato 2']
  statoCorrente = 'Stato 0';
  titleMenuDrawer = "Aggiorna Stato"
  constructor() {}

  ngOnInit(): void {
    localStorage.setItem('userFullName', 'Simone Soldo');

  }


}
