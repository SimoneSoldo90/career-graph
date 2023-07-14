import { AfterViewInit, Component } from '@angular/core';
import { ThemePalette } from '@angular/material/core';
import { ProgressBarMode } from '@angular/material/progress-bar';
import { Skill } from 'src/app/core/models/skill';
import { SkillService } from 'src/app/core/services/skill.service';

@Component({
  selector: 'app-roadmapgraph',
  templateUrl: './roadmapgraph.component.html',
  styleUrls: ['./roadmapgraph.component.css'],
})
export class RoadmapgraphComponent implements AfterViewInit {
  color: ThemePalette = 'primary';
  mode: ProgressBarMode = 'buffer';
  progressBarValue = 50;
  progressBarBufferValue = 50;
  title = 'Java';
  icon = 'java';
  description =
    "Oracle Java è al primo posto tra i linguaggi di programmazione e le piattaforme di sviluppo. Riduce i costi e i tempi di sviluppo, promuove l'innovazione e migliora i servizi applicativi. Con milioni di sviluppatori che eseguono oltre 60 miliardi di Java Virtual Machine in tutto il mondo, Java continua a essere la piattaforma di sviluppo preferita da aziende e sviluppatori.";
  heightOffset = 0;
  newDataset = {
    id: 1,
    title: 'Java',
    description:
      "Oracle Java è al primo posto tra i linguaggi di programmazione e le piattaforme di sviluppo. Riduce i costi e i tempi di sviluppo, promuove l'innovazione e migliora i servizi applicativi. Con milioni di sviluppatori che eseguono oltre 60 miliardi di Java Virtual Machine in tutto il mondo, Java continua a essere la piattaforma di sviluppo preferita da aziende e sviluppatori.",
    steps: [
      {
        id: 1,
        order: 1,
        title: 'Learn the foundamentals',
        description:
          'Java is a programming language and computing platform first released by Sun Microsystems in 1995. Java is a general-purpose, class-based, object-oriented programming language designed for having lesser implementation dependencies. It is a computing platform for application development. Java is fast, secure, and reliable, therefore. It is widely used for developing Java applications in laptops, data centers, game consoles, scientific supercomputers, cell phones, etc. Learn about the fundamentals of Java such as basic syntax, data types, variables, conditionals, functions, data structures, packages, etc.',
        skills: [
          {
            id: 1,
            title: 'Basic Syntax',
            description:
              'Understanding the basics is the key to a solid foundation. In this section, learn the basic terminologies, naming conventions, reserved words, conditions, functions, data structures, OOP, packages, etc.',
          },
        ],
      },
      {
        id: 2,
        order: 2,
        title: 'Build Tools',
        description:
          'A build tool is a program or command-line utility that automates the process of compiling, assembling, and deploying software.',
        skills: [
          {
            id: 11,
            title: 'Maven',
            description:
              'Maven is an open-source build tool, used primarily for Java projects.',
          },
        ],
      },
      {
        id: 3,
        order: 3,
        title: 'ORM',
        description:
          'Object-oriented programming is a core of Java Programming, which is used for designing a program using classes and objects. This can also be characterized as data controlling for accessing the code.',
        skills: [
          {
            id: 1,
            title: 'Hibernate',
            description:
              'Hibernate is an open source object-relational mapping tool that provides a framework to map object-oriented domain models to relational databases for web applications.',
          },
        ],
      },
    ],
  };
  dataset = [
    {
      id: 1,
      title: 'Java',
      childs: [2, 6, 7, 8, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21],
      // childs: [2,6,7,8,11,12,13,14]
    },
    {
      id: 2,
      title: 'OOP',
    },
    {
      id: 3,
      title: 'Build Tools',
      childs: [4, 5, 9, 10],
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
    },
    {
      id: 8,
      title: 'Pluto',
    },
    {
      id: 9,
      title: 'Paperino',
    },
    {
      id: 10,
      title: 'Topolino',
    },
    {
      id: 11,
      title: 'Zio Paperone',
    },
    {
      id: 12,
      title: 'Qui',
    },
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
      childs: [1, 22, 23, 24, 25, 26, 27, 28, 29],
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
      childs: [30, 31],
    },
    {
      id: 34,
      title: 'Softwareopoli2',
      childs: [32, 33],
      // childs: [2,6,7,8,11,12,13,14]
    },
  ];

  constructor(private skillService: SkillService) {}
  ngAfterViewInit(): void {
    this.heightOffset = document.getElementById('description')!.clientHeight;
  }
  visualizeSkills(event: Skill) {
    this.getSkillById(Number(event.id));
  }

  getSkillById(skillId: number): void {
    this.skillService.getSkillById(skillId).subscribe({
      next: (data: Skill) => {
        this.skillService.changeMessage(data);
      },
    });
  }
}
