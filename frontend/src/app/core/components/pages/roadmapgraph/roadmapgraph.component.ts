import { Component, OnInit, ViewChild } from '@angular/core';
import { Skill } from 'src/app/core/models/skill';
import { RoadmapService } from 'src/app/core/services/roadmap/roadmap.service';
import { ActivatedRoute } from '@angular/router';
import { SkillService } from 'src/app/core/services/skill/skill.service';
import { Edge } from '@swimlane/ngx-graph';
@Component({
  selector: 'app-roadmapgraph',
  templateUrl: './roadmapgraph.component.html',
  styleUrls: ['./roadmapgraph.component.css']
})
export class RoadmapgraphComponent implements OnInit{
  @ViewChild('childGraph',{static:true}) childGraph!: any;
  yourData: any = []
  yourLinks: Edge[] = []
  id:number;
  inputData:any;
  constructor(private roadmapservice: RoadmapService,private route: ActivatedRoute, private skillService: SkillService){
    this.inputData= this.route.snapshot.queryParamMap;
    this.id = JSON.parse(this.inputData.get('id'))
  }

  ngOnInit(): void {
    this.yourData = []
    this.roadmapservice.getRoadmap(this.id).subscribe({
      next: (data:Skill[])=>{
        data.forEach(element => {
          console.log(element)
          if(element.parentSkillId){
          this.yourData.push( {
            id: element.id,
            title: element.title,
            description:element.description,
            parent: element.parentSkillId
          })
          const link : Edge = { source: String(element.parentSkillId), target: String(element.id),label:"Pollo"}
          this.yourLinks.push(link)
        } else {
          this.yourData.push( {
            id: element.id,
            title: element.title,
            description:element.description,
            parent: element.id
          })
          // const link : Edge = { source: String(element.id), target: String(element.id),label:"Pollo"}
          // this.yourLinks.push(link);
        }
        })
        this.childGraph.createGraph(this.yourData,this.yourLinks)
      },
      error(err) {

      },
    });
  }
  visualizeSkills(event: Skill){
    this.getSkill(Number(event.id));
  }

  getSkill(skillId: number): void {
    this.skillService.getSkill(skillId).subscribe( {
      next: (data: Skill) => {
        let dataToPass = data;
        this.skillService.changeMessage(dataToPass);
      }
    })
  }
}
