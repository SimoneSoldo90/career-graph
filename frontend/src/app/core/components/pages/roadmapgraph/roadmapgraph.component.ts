import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { ThemePalette } from '@angular/material/core';
import { ProgressBarMode } from '@angular/material/progress-bar';
import { Roadmap } from 'src/app/core/models/roadmap';
import { Skill } from 'src/app/core/models/skill';
import { SkillService } from 'src/app/core/services/skill.service';
import { MindMapComponent } from '../../shared/mind-map/mind-map.component';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-roadmapgraph',
  templateUrl: './roadmapgraph.component.html',
  styleUrls: ['./roadmapgraph.component.css'],
})
export class RoadmapgraphComponent implements AfterViewInit {

  @ViewChild("roadmapGraph") roadmapGraph!: MindMapComponent;
  color: ThemePalette = 'primary';
  mode: ProgressBarMode = 'determinate';
  progressBarValue = 50;
  progressBarBufferValue = 50;
  title = 'Java';
  icon = 'java';
  description =
    "Oracle Java è al primo posto tra i linguaggi di programmazione e le piattaforme di sviluppo. Riduce i costi e i tempi di sviluppo, promuove l'innovazione e migliora i servizi applicativi. Con milioni di sviluppatori che eseguono oltre 60 miliardi di Java Virtual Machine in tutto il mondo, Java continua a essere la piattaforma di sviluppo preferita da aziende e sviluppatori.";
  heightOffset = 0;
  dataset: Roadmap = {
    id: 1,
    title: 'Roadmap Example',
    description: 'This is another example roadmap',
    steps: [
      {
        id: 1,
        roadmap_id: 1,
        order: 1,
        title: 'Roadmap Java',
        description: 'Description of Step 1',
        resources: [
          {
            step_id: 1,
            skill_id: 1,
            type: 'Article',
            description: 'Article about Step 1',
            url: 'https://example.com/article1',
          },
        ],
        roadmap_links: [
          {
            step_id: 1,
            roadmap_id: 2,
            roadmap_title: 'Related Roadmap 1',
            roadmap_description: 'Description of Related Roadmap 1',
          },
          {
            step_id: 1,
            roadmap_id: 3,
            roadmap_title: 'Basi di Dati',
            roadmap_description: 'Description of Related Roadmap 2',
          },
          {
            step_id: 1,
            roadmap_id: 4,
            roadmap_title: 'Related Roadmap 3',
            roadmap_description: 'Description of Related Roadmap 3',
          },
        ],
        skills: [
          {
            id: 1,
            step_id: 1,
            title: 'Skill 1',
            description: 'Description of Skill 1',
            resources: [
              {
                step_id: 1,
                skill_id: 1,
                type: 'Book',
                description: 'Book about Skill 1',
                url: 'https://example.com/book1',
              },
            ],
          },
          {
            id: 2,
            step_id: 1,
            title: 'Skill 2',
            description: 'Description of Skill 2',
            resources: [
              {
                step_id: 1,
                skill_id: 2,
                type: 'Video',
                description: 'Video tutorial for Skill 2',
                url: 'https://example.com/video2',
              },
            ],
          },
          {
            id: 3,
            step_id: 1,
            title: 'Skill 3',
            description: 'Description of Skill 3',
            resources: [
              {
                step_id: 1,
                skill_id: 3,
                type: 'Tutorial',
                description: 'Tutorial for Skill 3',
                url: 'https://example.com/tutorial3',
              },
            ],
          },
          {
            id: 4,
            step_id: 1,
            title: 'Skill 4',
            description: 'Description of Skill 4',
            resources: [
              {
                step_id: 1,
                skill_id: 4,
                type: 'Course',
                description: 'Course for Skill 4',
                url: 'https://example.com/course4',
              },
            ],
          },
        ],
      },
      {
        id: 2,
        roadmap_id: 1,
        order: 2,
        title: 'Step 2',
        description: 'Description of Step 2',
        resources: [
          {
            step_id: 2,
            skill_id: 5,
            type: 'Article',
            description: 'Article about Step 2',
            url: 'https://example.com/article2',
          },
        ],
        roadmap_links: [
          {
            step_id: 2,
            roadmap_id: 5,
            roadmap_title: 'Related Roadmap 4',
            roadmap_description: 'Description of Related Roadmap 4',
          },
          {
            step_id: 2,
            roadmap_id: 6,
            roadmap_title: 'Related Roadmap 5',
            roadmap_description: 'Description of Related Roadmap 5',
          },
        ],
        skills: [
          {
            id: 5,
            step_id: 2,
            title: 'Skill 5',
            description: 'Description of Skill 5',
            resources: [
              {
                step_id: 2,
                skill_id: 5,
                type: 'Book',
                description: 'Book about Skill 5',
                url: 'https://example.com/book5',
              },
            ],
          },
          {
            id: 6,
            step_id: 2,
            title: 'Skill 6',
            description: 'Description of Skill 6',
            resources: [
              {
                step_id: 2,
                skill_id: 6,
                type: 'Video',
                description: 'Video tutorial for Skill 6',
                url: 'https://example.com/video6',
              },
            ],
          },
          {
            id: 7,
            step_id: 2,
            title: 'Skill 7',
            description: 'Description of Skill 7',
            resources: [
              {
                step_id: 2,
                skill_id: 7,
                type: 'Tutorial',
                description: 'Tutorial for Skill 7',
                url: 'https://example.com/tutorial7',
              },
            ],
          },
          {
            id: 8,
            step_id: 2,
            title: 'Skill 8',
            description: 'Description of Skill 8',
            resources: [
              {
                step_id: 2,
                skill_id: 8,
                type: 'Course',
                description: 'Course for Skill 8',
                url: 'https://example.com/course8',
              },
            ],
          },
        ],
      },
      {
        id: 3,
        roadmap_id: 1,
        order: 3,
        title: 'Step 3',
        description: 'Description of Step 3',
        resources: [],
        roadmap_links: [
          // {
          //   step_id: 3,
          //   roadmap_id: 7,
          //   roadmap_title: 'Related Roadmap 6',
          //   roadmap_description: 'Description of Related Roadmap 6',
          // },
          // {
          //   step_id: 3,
          //   roadmap_id: 8,
          //   roadmap_title: 'Related Roadmap 7',
          //   roadmap_description: 'Description of Related Roadmap 7',
          // },
        ],
        skills: [
          {
            id: 9,
            step_id: 3,
            title: 'Skill 9',
            description: 'Description of Skill 9',
            resources: [
              {
                step_id: 3,
                skill_id: 9,
                type: 'Book',
                description: 'Book about Skill 9',
                url: 'https://example.com/book9',
              },
            ],
          },
          {
            id: 10,
            step_id: 3,
            title: 'Skill 10',
            description: 'Description of Skill 10',
            resources: [
              {
                step_id: 3,
                skill_id: 10,
                type: 'Video',
                description: 'Video tutorial for Skill 10',
                url: 'https://example.com/video10',
              },
            ],
          },
          {
            id: 11,
            step_id: 3,
            title: 'Skill 11',
            description: 'Description of Skill 11',
            resources: [
              {
                step_id: 3,
                skill_id: 11,
                type: 'Tutorial',
                description: 'Tutorial for Skill 11',
                url: 'https://example.com/tutorial11',
              },
            ],
          },
          {
            id: 12,
            step_id: 3,
            title: 'Skill 12',
            description: 'Description of Skill 12',
            resources: [
              {
                step_id: 3,
                skill_id: 12,
                type: 'Course',
                description: 'Course for Skill 12',
                url: 'https://example.com/course12',
              },
            ],
          },
        ],
      },
      {
        id: 4,
        roadmap_id: 1,
        order: 4,
        title: 'Step 4',
        description: 'Description of Step 4',
        resources: [
          {
            step_id: 4,
            skill_id: 13,
            type: 'Article',
            description: 'Article about Step 4',
            url: 'https://example.com/article4',
          },
        ],
        roadmap_links: [
          {
            step_id: 4,
            roadmap_id: 9,
            roadmap_title: 'Related Roadmap 8',
            roadmap_description: 'Description of Related Roadmap 8',
          },
          {
            step_id: 4,
            roadmap_id: 10,
            roadmap_title: 'Related Roadmap 9',
            roadmap_description: 'Description of Related Roadmap 9',
          },
          {
            step_id: 4,
            roadmap_id: 12,
            roadmap_title: 'Related Roadmap 10',
            roadmap_description: 'Description of Related Roadmap 10',
          },
          {
            step_id: 4,
            roadmap_id: 13,
            roadmap_title: 'Related Roadmap 11',
            roadmap_description: 'Description of Related Roadmap 11',
          },
          {
            step_id: 4,
            roadmap_id: 14,
            roadmap_title: 'Related Roadmap 12',
            roadmap_description: 'Description of Related Roadmap 12',
          },
        ],
        skills: [
          {
            id: 13,
            step_id: 4,
            title: 'Skill 13',
            description: 'Description of Skill 13',
            resources: [
              {
                step_id: 4,
                skill_id: 13,
                type: 'Book',
                description: 'Book about Skill 13',
                url: 'https://example.com/book13',
              },
            ],
          },
          {
            id: 14,
            step_id: 4,
            title: 'Skill 14',
            description: 'Description of Skill 14',
            resources: [
              {
                step_id: 4,
                skill_id: 14,
                type: 'Video',
                description: 'Video tutorial for Skill 14',
                url: 'https://example.com/video14',
              },
            ],
          },
          {
            id: 15,
            step_id: 4,
            title: 'Skill 15',
            description: 'Description of Skill 15',
            resources: [
              {
                step_id: 4,
                skill_id: 15,
                type: 'Tutorial',
                description: 'Tutorial for Skill 15',
                url: 'https://example.com/tutorial15',
              },
            ],
          },
          {
            id: 16,
            step_id: 4,
            title: 'Skill 16',
            description: 'Description of Skill 16',
            resources: [
              {
                step_id: 4,
                skill_id: 16,
                type: 'Course',
                description: 'Course for Skill 16',
                url: 'https://example.com/course16',
              },
            ],
          },
        ],
      },
      {
        id: 5,
        roadmap_id: 1,
        order: 5,
        title: 'Step 5',
        description: 'Description of Step 5',
        resources: [],
        roadmap_links: [],
        skills: [
          {
            id: 17,
            step_id: 5,
            title: 'Skill 17',
            description: 'Description of Skill 17',
            resources: [
              {
                step_id: 5,
                skill_id: 17,
                type: 'Book',
                description: 'Book about Skill 17',
                url: 'https://example.com/book17',
              },
            ],
          },
          {
            id: 18,
            step_id: 5,
            title: 'Skill 18',
            description: 'Description of Skill 18',
            resources: [
              {
                step_id: 5,
                skill_id: 18,
                type: 'Video',
                description: 'Video tutorial for Skill 18',
                url: 'https://example.com/video18',
              },
            ],
          },
          {
            id: 19,
            step_id: 5,
            title: 'Skill 19',
            description: 'Description of Skill 19',
            resources: [
              {
                step_id: 5,
                skill_id: 19,
                type: 'Tutorial',
                description: 'Tutorial for Skill 19',
                url: 'https://example.com/tutorial19',
              },
            ],
          },
          {
            id: 20,
            step_id: 5,
            title: 'Skill 20',
            description: 'Description of Skill 20',
            resources: [
              {
                step_id: 5,
                skill_id: 20,
                type: 'Course',
                description: 'Course for Skill 20',
                url: 'https://example.com/course20',
              },
            ],
          },
        ],
      },
    ],
  };
  private datasetTemp :Roadmap = this.dataset
private dataSet2 :Roadmap = {
  "id": 1,
  "title": "Roadmap Example",
  "description": "This is another example roadmap",
  "steps": [
    {
      "id": 1,
      "roadmap_id": 1,
      "order": 1,
      "title": "Basi di Dati",
      "description": "Description of Step 1",
      "resources": [
        {
          "step_id": 1,
          "skill_id": 1,
          "type": "Article",
          "description": "Article about Step 1",
          "url": "https://example.com/article1"
        }
      ],
      "roadmap_links": [],
      "skills": [
        {
          "id": 1,
          "step_id": 1,
          "title": "Skill 1",
          "description": "Description of Skill 1",
          "resources": [
            {
              "step_id": 1,
              "skill_id": 1,
              "type": "Book",
              "description": "Book about Skill 1",
              "url": "https://example.com/book1"
            }
          ]
        },
        {
          "id": 2,
          "step_id": 1,
          "title": "Skill 2",
          "description": "Description of Skill 2",
          "resources": [
            {
              "step_id": 1,
              "skill_id": 2,
              "type": "Video",
              "description": "Video tutorial for Skill 2",
              "url": "https://example.com/video2"
            }
          ]
        },
        {
          "id": 3,
          "step_id": 1,
          "title": "Skill 3",
          "description": "Description of Skill 3",
          "resources": [
            {
              "step_id": 1,
              "skill_id": 3,
              "type": "Tutorial",
              "description": "Tutorial for Skill 3",
              "url": "https://example.com/tutorial3"
            }
          ]
        },
        {
          "id": 4,
          "step_id": 1,
          "title": "Skill 4",
          "description": "Description of Skill 4",
          "resources": [
            {
              "step_id": 1,
              "skill_id": 4,
              "type": "Course",
              "description": "Course for Skill 4",
              "url": "https://example.com/course4"
            }
          ]
        }
      ]
    },
    {
      "id": 2,
      "roadmap_id": 1,
      "order": 2,
      "title": "Step 2",
      "description": "Description of Step 2",
      "resources": [
        {
          "step_id": 2,
          "skill_id": 5,
          "type": "Article",
          "description": "Article about Step 2",
          "url": "https://example.com/article2"
        }
      ],
      "roadmap_links": [],
      "skills": [
        {
          "id": 5,
          "step_id": 2,
          "title": "Skill 5",
          "description": "Description of Skill 5",
          "resources": [
            {
              "step_id": 2,
              "skill_id": 5,
              "type": "Book",
              "description": "Book about Skill 5",
              "url": "https://example.com/book5"
            }
          ]
        },
        {
          "id": 6,
          "step_id": 2,
          "title": "Skill 6",
          "description": "Description of Skill 6",
          "resources": [
            {
              "step_id": 2,
              "skill_id": 6,
              "type": "Video",
              "description": "Video tutorial for Skill 6",
              "url": "https://example.com/video6"
            }
          ]
        },
        {
          "id": 7,
          "step_id": 2,
          "title": "Skill 7",
          "description": "Description of Skill 7",
          "resources": [
            {
              "step_id": 2,
              "skill_id": 7,
              "type": "Tutorial",
              "description": "Tutorial for Skill 7",
              "url": "https://example.com/tutorial7"
            }
          ]
        },
        {
          "id": 8,
          "step_id": 2,
          "title": "Skill 8",
          "description": "Description of Skill 8",
          "resources": [
            {
              "step_id": 2,
              "skill_id": 8,
              "type": "Course",
              "description": "Course for Skill 8",
              "url": "https://example.com/course8"
            }
          ]
        }
      ]
    },
    {
      "id": 3,
      "roadmap_id": 1,
      "order": 3,
      "title": "Step 3",
      "description": "Description of Step 3",
      "resources": [],
      "roadmap_links": [],
      "skills": [
        {
          "id": 9,
          "step_id": 3,
          "title": "Skill 9",
          "description": "Description of Skill 9",
          "resources": [
            {
              "step_id": 3,
              "skill_id": 9,
              "type": "Book",
              "description": "Book about Skill 9",
              "url": "https://example.com/book9"
            }
          ]
        },
        {
          "id": 10,
          "step_id": 3,
          "title": "Skill 10",
          "description": "Description of Skill 10",
          "resources": [
            {
              "step_id": 3,
              "skill_id": 10,
              "type": "Video",
              "description": "Video tutorial for Skill 10",
              "url": "https://example.com/video10"
            }
          ]
        },
        {
          "id": 11,
          "step_id": 3,
          "title": "Skill 11",
          "description": "Description of Skill 11",
          "resources": [
            {
              "step_id": 3,
              "skill_id": 11,
              "type": "Tutorial",
              "description": "Tutorial for Skill 11",
              "url": "https://example.com/tutorial11"
            }
          ]
        },
        {
          "id": 12,
          "step_id": 3,
          "title": "Skill 12",
          "description": "Description of Skill 12",
          "resources": [
            {
              "step_id": 3,
              "skill_id": 12,
              "type": "Course",
              "description": "Course for Skill 12",
              "url": "https://example.com/course12"
            }
          ]
        }
      ]
    },
    {
      "id": 4,
      "roadmap_id": 1,
      "order": 4,
      "title": "Step 4",
      "description": "Description of Step 4",
      "resources": [
        {
          "step_id": 4,
          "skill_id": 13,
          "type": "Article",
          "description": "Article about Step 4",
          "url": "https://example.com/article4"
        }
      ],
      "roadmap_links": [
        {
          "step_id": 4,
          "roadmap_id": 1,
          "roadmap_title": "Roadmap Java",
          "roadmap_description": "Description of Related Roadmap 1"
        },
        {
          "step_id": 4,
          "roadmap_id": 3,
          "roadmap_title": "Related Roadmap 2",
          "roadmap_description": "Description of Related Roadmap 2"
        }
      ],
      "skills": [
        {
          "id": 13,
          "step_id": 4,
          "title": "Skill 13",
          "description": "Description of Skill 13",
          "resources": [
            {
              "step_id": 4,
              "skill_id": 13,
              "type": "Book",
              "description": "Book about Skill 13",
              "url": "https://example.com/book13"
            }
          ]
        },
        {
          "id": 14,
          "step_id": 4,
          "title": "Skill 14",
          "description": "Description of Skill 14",
          "resources": [
            {
              "step_id": 4,
              "skill_id": 14,
              "type": "Video",
              "description": "Video tutorial for Skill 14",
              "url": "https://example.com/video14"
            }
          ]
        },
        {
          "id": 15,
          "step_id": 4,
          "title": "Skill 15",
          "description": "Description of Skill 15",
          "resources": [
            {
              "step_id": 4,
              "skill_id": 15,
              "type": "Tutorial",
              "description": "Tutorial for Skill 15",
              "url": "https://example.com/tutorial15"
            }
          ]
        },
        {
          "id": 16,
          "step_id": 4,
          "title": "Skill 16",
          "description": "Description of Skill 16",
          "resources": [
            {
              "step_id": 4,
              "skill_id": 16,
              "type": "Course",
              "description": "Course for Skill 16",
              "url": "https://example.com/course16"
            }
          ]
        }
      ]
    },
    {
      "id": 5,
      "roadmap_id": 1,
      "order": 5,
      "title": "Step 5",
      "description": "Description of Step 5",
      "resources": [],
      "roadmap_links": [],
      "skills": [
        {
          "id": 17,
          "step_id": 5,
          "title": "Skill 17",
          "description": "Description of Skill 17",
          "resources": [
            {
              "step_id": 5,
              "skill_id": 17,
              "type": "Book",
              "description": "Book about Skill 17",
              "url": "https://example.com/book17"
            }
          ]
        },
        {
          "id": 18,
          "step_id": 5,
          "title": "Skill 18",
          "description": "Description of Skill 18",
          "resources": [
            {
              "step_id": 5,
              "skill_id": 18,
              "type": "Video",
              "description": "Video tutorial for Skill 18",
              "url": "https://example.com/video18"
            }
          ]
        },
        {
          "id": 19,
          "step_id": 5,
          "title": "Skill 19",
          "description": "Description of Skill 19",
          "resources": [
            {
              "step_id": 5,
              "skill_id": 19,
              "type": "Tutorial",
              "description": "Tutorial for Skill 19",
              "url": "https://example.com/tutorial19"
            }
          ]
        },
        {
          "id": 20,
          "step_id": 5,
          "title": "Skill 20",
          "description": "Description of Skill 20",
          "resources": [
            {
              "step_id": 5,
              "skill_id": 20,
              "type": "Course",
              "description": "Course for Skill 20",
              "url": "https://example.com/course20"
            }
          ]
        }
      ]
    }
  ]
};
  showSpinner: boolean = false;
  constructor(private skillService: SkillService,private route: ActivatedRoute,private router :Router) {
    this.route.queryParams
      .subscribe((params:any) => {

        if(params.id!=="1"){
          this.datasetTemp = this.dataset
      this.dataset = this.dataSet2;
      this.dataSet2 = this.datasetTemp
        }
      }
    );

  }

  ngAfterViewInit(): void {
    this.heightOffset = document.getElementById('description')!.clientHeight;
  }
  visualizeSkills(event: any) {
    if(event.isRoadmap){

      this.router.navigate(["/mindmap",{id:event.id}])
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
      this.showSpinner = event

    }
}
