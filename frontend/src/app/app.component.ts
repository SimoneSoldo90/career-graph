import { Component, OnInit } from '@angular/core';
import { MatIconRegistry } from "@angular/material/icon";
import { DomSanitizer } from '@angular/platform-browser';
import { Color } from './core/enum/color.enum';

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
          statusColor: Color.todo,
          color:'black',
          fontWeight: '400',
      },
      {
          title: 'Doing',
          statusColor: Color.doing,
          color:'black',
          fontWeight: '400' ,
      },
      {
          title: 'Done',
          statusColor: Color.done,
          color:'black',
          fontWeight: '400',

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
     this.matIconRegistry.addSvgIcon(
      `circle-right-regular`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/img/circle-right-regular.svg")
    );
     this.matIconRegistry.addSvgIcon(
      `right-long-solid`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/img/right-long-solid.svg")
    );
  }

  ngOnInit(): void {
    localStorage.setItem('userFullName', 'Simone Soldo');
  }
}
