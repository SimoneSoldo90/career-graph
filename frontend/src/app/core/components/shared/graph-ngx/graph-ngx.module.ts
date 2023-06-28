import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgxGraphModule } from '@swimlane/ngx-graph';
import { GraphNgxComponent } from './graph-ngx.component';



@NgModule({
  declarations: [GraphNgxComponent],
  imports: [
    CommonModule,
    NgxGraphModule,
  ],exports:[GraphNgxComponent]
})
export class GraphNgxModule { }
