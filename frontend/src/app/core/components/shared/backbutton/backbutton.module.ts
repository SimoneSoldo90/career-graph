import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BackbuttonComponent } from './backbutton.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [BackbuttonComponent],
  imports: [
    CommonModule,
    MatIconModule,
    MatButtonModule,
    RouterModule,
  ],exports:[BackbuttonComponent],
})
export class BackbuttonModule { }
