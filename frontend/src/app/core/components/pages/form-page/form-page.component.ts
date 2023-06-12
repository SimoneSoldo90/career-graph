import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-form-page',
  templateUrl: './form-page.component.html',
  styleUrls: ['./form-page.component.css']
})
export class FormPageComponent implements OnInit {

  formOptions!: any;

  ngOnInit(): void {
    this.formOptions = {
      "formObject": {
                      "id": 1, "title": "Java", "description": "Java è uno dei linuguaggi OOP più utilizzati", "enabled": true
                    },
      "typeRoadmap": "roadmap",
      "pageTitle": "Roadmap",
      "isCreation": false,
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
}
