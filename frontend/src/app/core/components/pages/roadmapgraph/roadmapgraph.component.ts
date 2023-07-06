import { Component } from '@angular/core';
import { Skill } from 'src/app/core/models/skill';
import { SkillService } from 'src/app/core/services/skill/skill.service';

@Component({
  selector: 'app-roadmapgraph',
  templateUrl: './roadmapgraph.component.html',
  styleUrls: ['./roadmapgraph.component.css'],
})
export class RoadmapgraphComponent {

  dataset = [
    {
      id: 1,
      title: 'Java',
      childs: [2,6,7,8,11,12,13,14],
    },
    {
      id: 2,
      title: 'OOP',
    },
    {
      id: 3,
      title: 'Build Tools',
      childs: [4,5,9,10],
    },
    {
      id: 4,
      title: 'Maven',
    },
    {
      id: 5,
      title: 'Gradle',
    },
    {
      id: 6,
      title: 'Types',
    },
    ,
    {
      id: 7,
      title: 'Pippo',
    }
    ,
    {
      id: 8,
      title: 'Pluto',
    }
    ,
    {
      id: 9,
      title: 'Paperino',
    }
    ,
    {
      id: 10,
      title: 'Topolino',
    }
    ,
    {
      id: 11,
      title: 'Zio Paperone',
    }
    ,
    {
      id: 12,
      title: 'Qui',
    }
    ,
    {
      id: 13,
      title: 'Quo',
    },

    {
      id: 14,
      title: 'Qua',
    }
  ];

  constructor(private skillService: SkillService) {}
  visualizeSkills(event: Skill) {
    this.getSkill(Number(event.id));
  }

  getSkill(skillId: number): void {
    this.skillService.getSkill(skillId).subscribe({
      next: (data: Skill) => {
        this.skillService.changeMessage(data);
      },
    });
  }
}
