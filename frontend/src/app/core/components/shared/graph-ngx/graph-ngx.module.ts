import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgxGraphModule } from '@swimlane/ngx-graph';
import { GraphNgxComponent } from './graph-ngx.component';
import {MatCardModule} from '@angular/material/card';



@NgModule({
  declarations: [GraphNgxComponent],
  imports: [
    CommonModule,
    NgxGraphModule,
    MatCardModule
  ],exports:[GraphNgxComponent]
})
export class GraphNgxModule { }
