import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SkillService } from 'src/app/core/services/skill.service';

@Component({
  selector: 'app-form-page',
  templateUrl: './form-page.component.html',
  styleUrls: ['./form-page.component.css'],
})
export class FormPageComponent implements OnInit {
  formOptions!: any;
  inputData: any;
  item: any;

  constructor(private route: ActivatedRoute, private skillService: SkillService) {}

  ngOnInit(): void {
    this.takeData();
    if (this.inputData.get('type') === 'roadmap') {
      if (JSON.parse(this.inputData.get('createMode'))) {
        this.formOptions = {
          "type": "roadmap",
          "pageTitle": "Roadmap",
          "isCreation": JSON.parse(this.inputData.get("createMode")),
          "method": "/roadmaps",
          "fields":[
            {"type":"text",
             "placeholder":"Scrivi qui...",
             "title":"Titolo",
             "id":"title",
             "required": true,
            },
            {
              type: 'text',
              placeholder: 'Scrivi qui...',
              title: 'Descrizione',
              id: 'description',
              required: true,
            },
            {
              type: 'boolean',
              placeholder: null,
              title: 'Enabled',
              id: 'enabled',
              required: false,
            },
          ],
        };
      } else {
        this.formOptions = {
          formObject: this.item,
          typeRoadmap: 'roadmap',
          pageTitle: 'Roadmap',
          isCreation: JSON.parse(this.inputData.get('createMode')),
          method: '/roadmaps',
          fields: [
            {
              type: 'text',
              placeholder: 'Scrivi qui...',
              title: 'Titolo',
              id: 'title',
              required: true,
            },
            {
              type: 'text',
              placeholder: 'Scrivi qui...',
              title: 'Descrizione',
              id: 'description',
              required: true,
            },
            {
              type: 'boolean',
              placeholder: null,
              title: 'Enabled',
              id: 'enabled',
              required: false,
            },
          ],
        };
      }
    } else if (this.inputData.get('type') === 'skills') {
      if (JSON.parse(this.inputData.get('createMode'))) {
        this.formOptions = {
          typeRoadmap: 'skill',
          pageTitle: 'Skill',
          isCreation: JSON.parse(this.inputData.get('createMode')),
          method: '/skills',
          fields: [
            {
              type: 'text',
              placeholder: 'Scrivi qui...',
              title: 'Titolo',
              id: 'title',
              required: true,
            },
            {
              "type": "text",
              "placeholder": "Scrivi qui...",
              "title": "Descrizione",
              "id": "description'",
              "required": true,
            },
            {
              "type": "boolean",
              "placeholder": null,
              "title": "Enabled",
              "id": "enabled",
              "required": false,
            },
          ],
        };
      } else {
      this.formOptions = {
        "formObject": this.item,
        "type": "roadmap",
        "pageTitle": "Roadmap",
        "isCreation": JSON.parse(this.inputData.get("createMode")),
        "method": "/roadmaps",
        "fields":[
          {"type":"text",
           "placeholder":"Scrivi qui...",
           "title":"Titolo",
           "id":"title",
           "required": true,
          },
          {"type":"text",
           "placeholder":"Scrivi qui...",
           "title":"Descrizione",
           "id": "description",
           "required": true,
          },
          {"type":"boolean",
           "placeholder":null,
           "title":"Enabled",
           "id": "enabled",
           "required": false,
          },
        ]
      }
    }
  } else if (this.inputData.get("type") === "skill"){
     if (JSON.parse(this.inputData.get("createMode"))){
       this.formOptions = {
        "type": "skill",
        "pageTitle": "Skill",
        "isCreation": JSON.parse(this.inputData.get("createMode")),
        "referenced": JSON.parse(this.inputData.get("referenced")),
        "method": "/skills",
        "fields":[
          {"type":"select",
          "placeholder":"Scrivi qui...",
          "title":"Skill Padre",
          "id":"parentSkill",
          "required": false,
          "options":[],
          "hasDetails": false},
          {"type":"text",
          "placeholder":"Scrivi qui...",
          "title":"Titolo",
          "id":"title",
          "required": true,
          },
           {"type":"text",
          "placeholder":"Scrivi qui...",
          "title":"Descrizione",
           "id": "description",
           "required": true,
          },
          {"type":"select",
          "placeholder":"Scrivi qui...",
          "title":"Risorsa web",
          "id":"resources",
          "required": false,
          "options":[],
          "hasDetails": true
          },
          {"type":"boolean",
          "placeholder":null,
          "title":"Enabled",
           "id": "enabled",
           "required": false,
           },
         ]
       }
     } else {
      this.formOptions = {
        "formObject": this.item,
        "type": "skill",
        "pageTitle": "Skill",
        "isCreation": JSON.parse(this.inputData.get("createMode")),
        "referenced": JSON.parse(this.inputData.get("referenced")),
        "method": "/skills",
        "fields":[
          {"type":"select",
          "placeholder":"Scrivi qui...",
          "title":"Skill Padre",
          "id":"parentSkill",
          "required": false,
          "hasDetails": false,
          "options": []
          },
          {"type":"text",
          "placeholder":"Scrivi qui...",
          "title":"Titolo",
          "id":"title",
          "required": true,
          },
           {"type":"text",
          "placeholder":"Scrivi qui...",
          "title":"Descrizione",
           "id": "description",
           "required": true,
          },
          {"type":"boolean",
           "placeholder":null,
           "title":"Enabled",
           "id": "enabled",
           "required": false,
           },
           {"type":"select",
          "placeholder":"Scrivi qui...",
          "title":"Risorsa web",
          "id":"resources",
          "required": true,
          "hasDetails": true,
          "options":[]
          }
         ]
       }
     }
   }
  }

  takeData() {
    this.inputData = this.route.snapshot.queryParamMap;
    this.item = JSON.parse(this.inputData.get('item'));
    }
  }

