import { Component, Input } from '@angular/core';
import { Node } from '@swimlane/ngx-graph';
@Component({
  selector: 'graph-ngx',
  templateUrl: './graph-ngx.component.html',
  styleUrls: ['./graph-ngx.component.css']
})
export class GraphNgxComponent {

  @Input() data!: { id: string; title: string; description: string; parent: string }[];

  nodes: Node[] = [];
  rectWidth = 100;
  rectHeight = 50;

  ngOnChanges() {
    this.nodes = this.createNodes();
  }

  private createNodes(): Node[] {
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
        }
      };
      nodes.push(node);
    });

    return nodes;
  }
}
