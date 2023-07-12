import { Component, OnInit } from '@angular/core';
import { MatIconRegistry } from "@angular/material/icon";
import { DomSanitizer } from '@angular/platform-browser';

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
          color:'black',
          fontWeight: '500',
      },
      {
          title: 'Doing',
          statusColor: '#74b9ff',
          color:'white',
          fontWeight: '500' ,
      },
      {
          title: 'Done',
          statusColor: '#00b894',
          color:'white',
          fontWeight: '500',

      },
    ],
  };
  titleMenuDrawer = 'Aggiorna Stato';
  constructor(private matIconRegistry: MatIconRegistry,  private domSanitizer: DomSanitizer
    ) {
    this.matIconRegistry.addSvgIcon(
      `java`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/img/java.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `graduation-cap-solid`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/img/graduation-cap-solid.svg")
    );
  }

  ngOnInit(): void {
    localStorage.setItem('userFullName', 'Simone Soldo');
  }
}
