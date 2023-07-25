import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Skill } from 'src/app/core/models/skill';
import { Resource } from 'src/app/core/models/resource.model';
import { ResourceService } from 'src/app/core/services/resource.service';
import { SkillService } from 'src/app/core/services/skill.service';

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
    if (this.formOptions.type === 'skill') {
      this.setDataSources();
      this.populateDropDownMenu();
    }
    this.createForm();

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
    } else {
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

      this.formOptions.fields.forEach((field: any) => {
        if (this.formOptions.formObject.hasOwnProperty(field.id)) {
            form[field.id] = this.formOptions.formObject[field.id];
        }
      });
      this.genericForm.patchValue(form);
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
        (resource) =>{}
          // resource.id === element.id
          //TODO DA REIMPLEMENTARE LOGICA
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
        (resource) =>{}
// resource.id === element.id
          //TODO DA REIMPLEMENTARE LOGICA
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
    this.skillService.getAllSkills().subscribe({
      next: (data: Skill[]) => {
        this.skillDataSources.push({ skills: data });
      },
    });
  }

  getResources(): void {
    //TODO DA REIMPLEMENTARE LOGICA
    this.resourceService.getResourcesByStepId(1).subscribe({
      next: (data: Resource[]) => {
        this.resourceDataSources.push({ resources: data });
      },
    });
  }

  setDataSources(): void {
    this.getResources();
    this.getSkills();
  }
}
