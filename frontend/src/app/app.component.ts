import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDrawer } from '@angular/material/sidenav';

import { Skill } from './core/models/skill';
import { Resource } from './core/models/resource';
import { SkillService } from './core/services/skill/skill.service';
import { Resourcedetail, getKeys } from './core/models/resourcedetail';

import { keys } from 'ts-transformer-keys';

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
