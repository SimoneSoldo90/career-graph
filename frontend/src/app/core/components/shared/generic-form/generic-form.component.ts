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
  skillDataSources: any = [];
  resourceDataSources: any = [];
  resourcesList: Resource[] = [];

  constructor(
    private skillService: SkillService,
    private resourceService: ResourceService,
  ) {}

  ngOnInit() {
    // console.log(this.formOptions);
    if (this.formOptions.type === 'skill') {
      this.setDataSources();
      this.populateDropDownMenu();
    }
    this.createForm();
    console.log(this.formOptions.isCreation);
    if (!this.formOptions.isCreation){
      this.populateForm()
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
      console.log(this.formOptions.formObject);
    } else {
      console.log(this.genericForm.value);
      const form = this.genericForm.value
       this.formOptions.formObject = {
         id: this.formOptions.formObject.id,
         title: form.title,
         description: form.description,
         enabled: form.enabled,
       };
    }
  }

  populateForm() {
    if (this.formOptions.formObject) {
      var form = this.genericForm.value;
      console.log(this.formOptions.formObject);
      this.formOptions.fields.forEach((field: any) => {
        if (this.formOptions.formObject.hasOwnProperty(field.id)) {
            form[field.id] = this.formOptions.formObject[field.id];
        }
      });
      this.genericForm.patchValue(form);
      console.log(form);
    }

  }

  getDataSource(fieldId: string): any[] {
    if (fieldId === 'parentSkill') {
      return this.skillDataSources[0]?.skills ?? [];
    } else if (fieldId === 'resources') {
      return this.resourceDataSources[0]?.resources ?? [];
    } else {
      return [];
    }
  }

  populateDropDownMenu() {
    this.formOptions.fields.forEach((field: any) => {
      if (this.formOptions.fields.id === 'parentSkill') {
        this.formOptions.fields.options = this.skillDataSources;
      } else if (this.formOptions.fields.id === 'resources') {
        this.formOptions.fields.options = this.resourceDataSources;
      }
    });
  }

  addElement(fieldId: string, element: Resource) {
    if (fieldId === 'resources') {
      const index = this.resourcesList.findIndex(
        (resource) =>
          resource.id === element.id
      );
      if (index === -1) {
        this.resourcesList.push(element);
        this.genericForm.value['resources'] = this.resourcesList;
        this.genericForm.patchValue(this.genericForm.value);
      }
    }
  }

  removeElement(fieldId: string, element: Resource, indice: number) {
    if (fieldId === 'resources') {
      const index = this.resourcesList.findIndex(
        (resource) =>
          resource.id === element.id
      );
        this.resourcesList.splice(indice, 1);
        this.genericForm.value['resources'] = this.resourcesList;
        this.genericForm.patchValue(this.genericForm.value);
      }
  }




  selectOption(fieldId: string, selected: any) {
    if (fieldId === 'parentSkill') {
      this.genericForm.value['parentSkill'] = selected.id;
      this.genericForm.patchValue(this.genericForm.value);
    }
    console.log(this.genericForm.value);
  }

  isAddButtonVisible(type: string, selected: any): boolean {
    if (type === 'resources') {
      if (selected) {
        return true;
      }
    }
    return false;
  }

  getSkills(): void {
    this.skillService.getSkills().subscribe({
      next: (data: Skill[]) => {
        this.skillDataSources.push({ skills: data });
      },
    });
    console.log(this.skillDataSources);
  }

  getResources(): void {
    this.resourceService.getResources().subscribe({
      next: (data: Resource[]) => {
        this.resourceDataSources.push({ resources: data });
      },
    });
    console.log(this.resourceDataSources);
  }

  setDataSources(): void {
    this.getResources();
    this.getSkills();
  }
}
