import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NavigationExtras, Router } from '@angular/router';

import { Roadmap } from 'src/app/core/models/roadmap';
import { RoadmapService } from 'src/app/core/services/roadmap/roadmap.service';
@Component({
  selector: 'app-roadmaps',
  templateUrl: './roadmaps.component.html',
  styleUrls: ['./roadmaps.component.css']
})
export class RoadmapsComponent implements OnInit {

  title = 'Lista Roadmap';
  detailTitle = '';
  dataSource: Roadmap[] = [];
  displayedColumns = ["id", "title"];
  tableDef: Array<any> = [
    {
      key: 'id',
      header: 'ID',
    },    {
      key: 'title',
      header: 'Titolo',
    },
  ]
  tableOptions = {
    "type": "roadmap",
    "displayedColumns": this.displayedColumns,
    "tableDef": this.tableDef,
    "canDelete": true,
    "canModify": true,
    btnCreate:{
      "canCreate":  true,
      "canView": true
    },
    "title": this.title,
    "detailTitle": this.detailTitle,
    "emptyData": false,
    "visualizzazioneGraficaPath":"/roadmapgraph"
  };
  constructor(private roadmapService: RoadmapService, private router: Router) {}

  ngOnInit(): void {
    this.getRoadmaps();
    // can delete e can modify da modificare in base al ruolo!!
  }

  getRoadmaps(): void {
    this.roadmapService.getRoadmaps().subscribe( {
      next: (data: Roadmap[]) => {
        this.dataSource = data;
      },
      error: (error: HttpErrorResponse) => {
        if(error.status === HttpStatusCode.NotFound){
          this.tableOptions.emptyData = true;
        } else {
          console.log(error.message)
        }
      }
    });
  }

  createNewRoadmap(event: boolean){
    if(event){
      this.router.navigate(['/form', { createMode: true, type: "roadmap" }]);
    }
  }
  visualizeRoadmaps(event: Roadmap){
    console.log("Ricevuto evento")
    this.router.navigate(["/roadmap",{elementId:event.id}])
  }
  visualizzaGrafo(event: Roadmap){
    this.router.navigate(['/roadmapgraph', { id: event.id }])
  }
}
