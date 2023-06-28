import { Component, OnInit } from '@angular/core';
import { RoadmapService } from 'src/app/core/services/roadmap/roadmap.service';
import { Node, Edge } from '@swimlane/ngx-graph';
import { Skill } from 'src/app/core/models/skill';
import * as shape from 'd3-shape';

@Component({
  selector: 'app-roadmap-graph',
  templateUrl: './roadmap-graph.component.html',
  styleUrls: ['./roadmap-graph.component.css'],
})
export class RoadmapGraphComponent implements OnInit {
  roadmap = window.history.state.options;
  skills: Skill[] = [];

  name = 'Angular 5';
  nodes: Node[] = [];
  links: Edge[] = [];
  public layoutSettings = 'elk'
  public curve: any = shape.curveLinear;
  curveType: string = 'Bundle';
  layout = 'dagre';

  constructor(private roadmapService: RoadmapService) {}

  public ngOnInit(): void {
    this.getSkills();
  }

  getSkills(): void {
    this.roadmapService.getRoadmap(this.roadmap.id).subscribe({
      next: (data: any[]) => {
        // console.log(this.roadmap);
        data.forEach((item: any) => {
          this.skills.push(item.skill);
        });
      },
      error: (error: Error) => {
        console.log('Errore:' + error);
      },
      complete: () => {
        this.setUpData();
      },
    });
  }

  setUpData(): void {
    this.nodes.push( {id: this.roadmap.title, label: this.roadmap.title});

    for (const skill of this.skills) {
      const node: Node = {
        id: String(skill.id),
        label: skill.title
      };
      this.nodes.push(node);
    }

    for (const skill of this.skills) {
      const edge: Edge = {
        source: this.roadmap.title,
        target: String(skill.id),
        label: '',
        data: {
          linkText: 'Legame tra link',
        }
      };
      this.links.push(edge);
    }
  }

    public getStyles(node: Node): any {
      return {
        "background-color": node.data.backgroundColor
      };
    }
}
