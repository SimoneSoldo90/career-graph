import { Component, Input } from '@angular/core';
import { Node } from '@swimlane/ngx-graph';

@Component({
  selector: 'graph-ngx',
  templateUrl: './graph-ngx.component.html',
  styleUrls: ['./graph-ngx.component.css']
})
export class GraphNgxComponent {


  @Input() set data(data:{ id: string; title: string; description: string; parent: string }[]){
    this.nodes = data;
  }
  @Input() set graphlinks(data : any[]){
    this.setGraphLinks(data);
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

  public createNodes(): Node[] {
    this.links = this.graphlinks;

    const nodes: Node[] = [];

    this.data.forEach(item => {
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
