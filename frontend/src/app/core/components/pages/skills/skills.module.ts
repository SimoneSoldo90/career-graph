import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SkillsComponent } from './skills.component';
import { GenericTableModule } from '../../shared/generic-table/generic-table.module';



@NgModule({
  declarations: [SkillsComponent],
  imports: [
    CommonModule,
    GenericTableModule,
  ],
  exports:[
    SkillsComponent
  ]
})
export class SkillsModule { }
