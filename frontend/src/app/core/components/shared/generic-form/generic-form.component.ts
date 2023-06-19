import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Skill } from 'src/app/core/models/skill';
import { Resource } from 'src/app/core/models/resource';
import { ResourceService } from 'src/app/core/services/resource/resource.service';
import { SkillService } from 'src/app/core/services/skill/skill.service';

@Component({
  selector: 'app-generic-form',
  templateUrl: './generic-form.component.html',
  styleUrls: ['./generic-form.component.css'],
})
export class GenericFormComponent implements OnInit {
  @Input() formOptions: any;
  genericForm!: FormGroup;
  booleantype = 'boolean';
  selected: any;
  dataSources: any = [];

  constructor(private skillService: SkillService, private resourceService: ResourceService) {}

  ngOnInit() {
    if (this.formOptions.type === 'skill') {
      this.setSkillDataSources();
    }
    this.createForm();
    console.log(this.formOptions.isCreation);
    if (!this.formOptions.isCreation) {
      this.populateForm();
    }
  }

  createForm() {
    var formControls: { [key: string]: FormControl } = {};

    for (const field of this.formOptions.fields) {
      if (field.required) {
        formControls[field.id] = new FormControl('', Validators.required);
      } else if (!field.required && field.type === 'boolean') {
        formControls[field.id] = new FormControl(false);
      } else if (field.type === 'select') {
        formControls[field.id] = new FormControl();
      } else {
        formControls[field.id] = new FormControl('');
      }
    }
    this.genericForm = new FormGroup(formControls);
  }

  onSubmit() {
    if (this.genericForm && this.genericForm.valid) {
      this.submit();
    }
  }

  submit() {
    if (!this.formOptions.isCreation) {
      const form = this.genericForm.value;
      this.formOptions.fields.forEach((field: any) => {
        if (field.id !== 'id') {
          this.formOptions.formObject[field.id] = form[field.id];
        }
      });
      // this.formOptions.formObject = {
      //   id: this.formOptions.formObject.id,
      //   title: form.title,
      //   description: form.description,
      //   enabled: form.enabled,
      // };
      console.log(this.formOptions.formObject);
    } else {
      console.log(this.genericForm.value);
    }
  }

  populateForm() {
    if (this.formOptions.formObject) {
      var form = this.genericForm.value;
      this.formOptions.fields.forEach((field: any) => {
        if (this.formOptions.formObject.hasOwnProperty(field.id)) {
          form[field.id] = this.formOptions.formObject[field.id];
        }
      });
      this.genericForm.patchValue(form)
    }
  }

  getSkills(): void {
    this.skillService.getSkills().subscribe( {
      next: (data: Skill[]) => {
        this.dataSources.push({skills: data});
    }});
  }

  getResources(): void {
    this.resourceService.getResources().subscribe( {
      next: (data: Resource[]) => {
        this.dataSources.push({resources: data});
    }});
    console.log(this.dataSources);
  }

  setSkillDataSources(): void {
    this.getResources();
    this.getSkills();
  }
}
