import { Component,Output,  EventEmitter,
} from '@angular/core';
import { DagreLayout, DagreNodesOnlySettings, Node, Orientation } from '@swimlane/ngx-graph';
import { Edge } from '@swimlane/ngx-graph/lib/models/edge.model';
import * as shape from 'd3-shape';

@Component({
  selector: 'graph-ngx',
  templateUrl: './graph-ngx.component.html',
  styleUrls: ['./graph-ngx.component.css']
})
export class GraphNgxComponent {

  @Output() showelement = new EventEmitter<any>();

  data:any;
  public curve: any = shape.curveBundle.beta(0.5);

  constructor(){

  }
  setGraphLinks(data: any[]) {
    this.links = data
  }
  nodes: Node[] = [];
  rectWidth = 100;
  rectHeight = 50;
  links!: Edge[];

  public layoutSettings = {
    orientation: 'TB'
  };
  ngOnChanges() {
    this.nodes = this.createNodes();
  }
  public createGraph(data:any,links:any){
    this.data = data;
    this.setGraphLinks(links);
    this.nodes = this.createNodes();
  }
  createNodes(): Node[] {
    //this.links = this.graphlinks;

    const nodes: Node[] = [];

    this.data.forEach((item:any) => {
      const node: Node = {
        id: item.id,
        label: item.title,
        data: {
          title: item.title,
          description: item.description,
          color: '#ffffff',
          textColor: '#000000'
        }
      };
      nodes.push(node);
    });

    return nodes;
  }

  show(event:any){
    this.showelement.emit(event);
  }

  public getStyles(node: Node): any {
    return {
      'heigth':'30',
      'width':'130',
      'padding':'10px'
    }
  }
}
