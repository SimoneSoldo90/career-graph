import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-form-page',
  templateUrl: './form-page.component.html',
  styleUrls: ['./form-page.component.css']
})
export class FormPageComponent implements OnInit {

  formOptions!: any;
  inputData: any;
  item: any;

  constructor(private route: ActivatedRoute){}

  ngOnInit(): void {
    this.takeData();
    this.formOptions = {
      "formObject": this.item,
      "typeRoadmap": "roadmap",
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

  takeData(){
    this.inputData = this.route.snapshot.queryParamMap;
    this.item = JSON.parse(this.inputData.get("item"))
  }
}
