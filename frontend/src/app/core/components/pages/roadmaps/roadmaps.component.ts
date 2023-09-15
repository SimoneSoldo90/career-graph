import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Roadmap } from 'src/app/core/models/roadmap';
import { TableOptions } from 'src/app/core/models/tableoption.model';
import { RoadmapService } from 'src/app/core/services/roadmap.service';
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
  tableOptions:TableOptions = {
    "type": "roadmap",
    "displayedColumns": this.displayedColumns,
    "tableDef": this.tableDef,
    "canDelete": true,
    "canModify": true,
    btnCreate:{
      "type":"roadmap",
      "title":"Aggiungi Roadmap",
      "canCreate":  true,
      "canView": true
    },
    "title": this.title,
    "detailTitle": this.detailTitle,
    "emptyData": false,
    btnVisualize:{
      canViewGraph:true,
      canView:false,
      tooltip:"Visualizza roadmap",
      routerLink:"/mindmap",
      queryParams:{id:1}
    },
    btnNavigate:{
      canNavigate:true
    }
  };
  constructor(private roadmapService: RoadmapService, private router: Router) {}

  ngOnInit(): void {
    this.getRoadmaps();
    // can delete e can modify da modificare in base al ruolo!!
  }

  getRoadmaps(): void {
    this.roadmapService.getAllRoadmaps().subscribe( {
      next: (data: Roadmap[]) => {
        this.dataSource = data;
      },
      error: (error: HttpErrorResponse) => {
        if(error.status === HttpStatusCode.NotFound){
          this.tableOptions.emptyData = true;
        } else {

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

    this.router.navigate(["/roadmap",{elementId:event.id}])
  }
  visualizzaGrafo(event: Roadmap){
    this.router.navigate(['/mindmap/'+event.id])
  }
  visualizzaRoadmap(event:any){
    this.router.navigate(['roadmap'], {
      state: { options: { elementId:event.element.id,  elementTitle:event.element.title } }
    });
  }
}
