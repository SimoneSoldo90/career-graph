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
      childs: [2,6,7,8,11,12,13,14,15,16,17,18,19,20,21],
      // childs: [2,6,7,8,11,12,13,14]
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
    },

    {
      id: 15,
      title: 'Gimli',
    },

    {
      id: 16,
      title: 'Legolas',
    },

    {
      id: 17,
      title: 'Pipino',
    },

    {
      id: 18,
      title: 'Merry',
    },

    {
      id: 19,
      title: 'Frodo',
    },

    {
      id: 20,
      title: 'Gandalf',
    },
    {
      id: 21,
      title: 'Softwareopoli',
      childs: [22,23,24,25,26,27,28,29,30,31,32,33],
      // childs: [2,6,7,8,11,12,13,14]
    },

    {
      id: 22,
      title: 'Simone',
    },

    {
      id: 23,
      title: 'Paolo',
    },

    {
      id: 24,
      title: 'Antonio',
    },

    {
      id: 25,
      title: 'Giuseppe',
    },

    {
      id: 26,
      title: 'Roberto',
    },

    {
      id: 27,
      title: 'Federico',
    },

    {
      id: 28,
      title: 'Nick',
    },

    {
      id: 29,
      title: 'Giuseppe P',
    },

    {
      id: 30,
      title: 'Daniele',
    },

    {
      id: 31,
      title: 'Gabriele',
    },

    {
      id: 32,
      title: 'Pier',
    },

    {
      id: 33,
      title: 'Carmine',
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