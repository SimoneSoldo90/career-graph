import { Component,OnInit, Input } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-generic-form',
  templateUrl: './generic-form.component.html',
  styleUrls: ['./generic-form.component.css']
})
export class GenericFormComponent implements OnInit {

  @Input() formOptions: any;
  genericForm!: FormGroup;
  booleantype = "boolean";

  constructor () {}

  ngOnInit() {
    this.createForm();
    if (!this.formOptions.isCreation){
      this.populateForm()
    }
  }

  createForm() {
    var formControls: { [key: string]: FormControl } = {};

    for (const field of this.formOptions.fields) {
      if (field.required){
        formControls[field.id] = new FormControl('', Validators.required);
      }
      else if (!field.required && field.type === 'boolean'){
        formControls[field.id] = new FormControl(false);
      }
      else {
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
      const form = this.genericForm.value
       this.formOptions.formObject = {
         id: this.formOptions.formObject.id,
         title: form.title,
         description: form.description,
         enabled: form.enabled,
       };
    }
    else {
    }
  }

  populateForm() {
    if (this.formOptions.formObject) {
      this.genericForm.patchValue({
        id:this.formOptions.formObject.id,
        title: this.formOptions.formObject.title,
        description: this.formOptions.formObject.description,
        enabled: this.formOptions.formObject.enabled
      });
    }
  }
}
