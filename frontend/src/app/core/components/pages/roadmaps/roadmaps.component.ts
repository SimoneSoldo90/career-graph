import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Roadmap } from 'src/app/core/models/roadmap';
import { RoadmapService } from 'src/app/core/services/roadmap/roadmap.service';

@Component({
  selector: 'app-roadmaps',
  templateUrl: './roadmaps.component.html',
  styleUrls: ['./roadmaps.component.css']
})
export class RoadmapsComponent implements OnInit {

  dataSource: Roadmap[] = [];
  // displayedColumns: string[] = [];
  displayedColumns = ["id", "title"];
  tableOptions = {
    "displayedColumns": this.displayedColumns,
    "canDelete": true,
    "canModify": true,
    btnCreate:{
      "canCreate": true,
      "canView": true
    }
  };
  constructor(private roadmapService: RoadmapService, private router: Router) {}

  ngOnInit(): void {
    this.getRoadmaps();
    // can delete e can modify da modificare in base al ruolo!!
  }

  getRoadmaps(): void {
    this.roadmapService.getRoadmap().subscribe((data) => {
        this.dataSource = data;
      // this.getType();
    });
  }

  createNewRoadmap(event: boolean){
    if(event){
      console.log('Redirect alla pagina di creazione Roadmap')
      this.router.navigate(['/skills']);
      // this.router.navigate(['/skills', { id: heroId }]);
    }
  }

  // getRoadmaps(): void {
  //   this.roadmapService.getRoadmap().subscribe( {
  //     next: (data: Roadmap[]) => {
  //       this.dataSource = data;
  //     }
  //     // this.getType();
  //   });
  // }

  // getType(): void{
  //   this.displayedColumns = Object.keys(this.dataSource[0]);
  //   console.log(this.displayedColumns);
  // }
}
