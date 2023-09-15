import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { ThemePalette } from '@angular/material/core';
import { ProgressBarMode } from '@angular/material/progress-bar';
import { Roadmap } from 'src/app/core/models/roadmap';
import { Skill } from 'src/app/core/models/skill';
import { SkillService } from 'src/app/core/services/skill.service';
import { MindMapComponent } from '../../shared/mind-map/mind-map.component';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { RoadmapService } from 'src/app/core/services/roadmap.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-roadmapgraph',
  templateUrl: './roadmapgraph.component.html',
  styleUrls: ['./roadmapgraph.component.css'],
})
export class RoadmapgraphComponent implements AfterViewInit {
  @ViewChild('roadmapGraph') roadmapGraph!: MindMapComponent;
  color: ThemePalette = 'primary';
  mode: ProgressBarMode = 'determinate';
  progressBarValue = 50;
  progressBarBufferValue = 50;
  title = '';
  icon = 'java';
  description =
    "";
  heightOffset = 0;
  dataset: Roadmap = { title: '', description: '' };
  showSpinner: boolean = false;
  constructor(
    private skillService: SkillService,
    private route: ActivatedRoute,
    private router: Router,
    private roadmapService: RoadmapService
  ) {
    /*this.route.queryParams
      .subscribe((params:any) => {

        if(params.id!=="1"){
          this.datasetTemp = this.dataset
      this.dataset = this.dataSet2;
      this.dataSet2 = this.datasetTemp
        }
      }
    );
*/
    this.route.queryParams.subscribe((params: any) => {
      if (params.id) {
        this.roadmapService.getRoadmapById(params.id).subscribe({
          next: (response: Roadmap) => {
            this.dataset = response;
            this.title = response.title;
            this.description = response.description;
          },
          error: (error: HttpErrorResponse) => {},
        });
      }
    });
  }

  ngAfterViewInit(): void {
    this.heightOffset = document.getElementById('description')!.clientHeight;
  }
  visualizeSkills(event: any) {
    if (event.isRoadmap) {
      this.router.navigate(['/mindmap', { id: event.id }]);
    } else {
      this.getSkillById(Number(event.id_db));
    }
  }

  getSkillById(skillId: number): void {
    this.skillService.getSkillById(skillId).subscribe({
      next: (data: Skill) => {
        this.skillService.changeMessage(data);
      },
    });
  }
  showMatSpinner(event: any) {
    this.showSpinner = event;
  }
}
