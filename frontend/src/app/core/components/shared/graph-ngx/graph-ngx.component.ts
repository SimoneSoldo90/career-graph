import { Component, Input } from '@angular/core';
import { Node } from '@swimlane/ngx-graph';

@Component({
  selector: 'graph-ngx',
  templateUrl: './graph-ngx.component.html',
  styleUrls: ['./graph-ngx.component.css']
})
export class GraphNgxComponent {


  data:any;

  constructor(){
    console.log("Costruttore graph-ngx")
  }
  setGraphLinks(data: any[]) {
    this.links = data
  }
  nodes: Node[] = [];
  rectWidth = 100;
  rectHeight = 50;
  links!: any[];
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
        },
      };
      nodes.push(node);
    });

    return nodes;
  }

}
