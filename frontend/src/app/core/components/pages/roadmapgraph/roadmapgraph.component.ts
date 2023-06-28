import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Skill } from 'src/app/core/models/skill';
import { RoadmapService } from 'src/app/core/services/roadmap/roadmap.service';
import { ActivatedRoute, NavigationExtras } from '@angular/router';

@Component({
  selector: 'app-roadmapgraph',
  templateUrl: './roadmapgraph.component.html',
  styleUrls: ['./roadmapgraph.component.css']
})
export class RoadmapgraphComponent implements OnInit{

  yourData: any = []
  yourLinks: any[] = []
  id:number;
  inputData:any;
  constructor(private roadmapservice: RoadmapService,private route: ActivatedRoute){
    this.inputData= this.route.snapshot.queryParamMap;
    console.log(JSON.parse(this.inputData.get('elementId')))
    this.id = JSON.parse(this.inputData.get('elementId'))
  }

  ngOnInit(): void {
    this.yourData = []
    this.roadmapservice.getRoadmap(this.id).subscribe({
      next: (data:Skill[])=>{
        data.forEach(element => {
          if(element.parentSkill){
          this.yourData.push( {
            id: element.id,
            title: element.title,
            description:element.description,
            parent: element.parentSkill.id
          })
          this.yourLinks.push({ source: element.parentSkill.id, target: element.id })
        } else {
          this.yourData.push( {
            id: element.id,
            title: element.title,
            description:element.description,
            parent: element.id
          })
          this.yourLinks.push({ source: element.id, target: element.id })
        }
        })
      }
    });
  }

}
