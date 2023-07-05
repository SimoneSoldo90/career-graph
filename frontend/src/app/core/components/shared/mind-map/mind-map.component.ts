import {
  AfterViewInit,
  Component,
  ElementRef,
  HostListener,
  OnInit,
  Renderer2,
  RendererFactory2,
  ViewChild,
} from '@angular/core';

@Component({
  selector: 'app-line-renderer',
  templateUrl: './mind-map.component.html',
  styles: [
    `
      div {
        position: relative;
      }
      canvas {
        display: block;
        position: absolute;
        top: 0;
        left: 0;
        z-index: -1;
      }
      mat-grid-list {
        padding: 150px;
      }
      mat-grid-tile {
        background: lightblue;
      }
      .button-skill {
        display: inline-block;
        outline: 0;
        border: 0;
        cursor: pointer;
        border-radius: 8px;
        padding: 10px 12px 10px;
        font-size: 18px;
        font-weight: 700;
        line-height: 1;
        background-color: white;
        transition: transform 200ms, background 200ms;
        color: #000000;
        box-shadow: 0 0 0 3px #000000 inset;
        margin-top: 25px;
      }
      .button-skill:hover {
        transform: translateY(-2px);
      }
      .leftTable {
        float: left;
        position: relative;
      }
      .centerTable {
        float: left;
        position: relative;
        margin: 20px 100px 0px 100px;
      }
      .rightTable {
        float: left;
        position: relative;
      }
      @media only screen and (max-width: 500px) {
        .centerTable {
          float: left;
          position: relative;
          margin: 20px 25px 0px 25px;
        }
      }
    `,
  ],
})
export class MindMapComponent implements AfterViewInit {
  firsthalfchilds: {id:number;title:string}[] = [];
  parents: { id: number; title: string; childs: number[] }[] = [];

  nodes = [
    {
      id: 1,
      title: 'Java',
      childs: [2, 6],
    },
    {
      id: 2,
      title: 'OOP',
    },
    {
      id: 3,
      title: 'Build Tools',
      childs: [4, 5],
    },
    {
      id: 4,
      title: 'Maven',
    },
    {
      id: 5,
      title: 'Gradle',
    },
    {
      id: 6,
      title: 'Types',
    },
  ];

  constructor() {
    this.nodes.forEach((node) => {
      if (node.childs) {
        this.parents.push(node);
        node.childs.forEach((child) => {
          let childFounded = this.nodes.filter((element) => {
            if (element.id == child) return element;
            else return null;
          });
          this.firsthalfchilds.push(childFounded[0]);
        });
      }
    });
  }

  ngAfterViewInit() {
    this.drawLine();
  }

  @HostListener('window:resize')
  onWindowResize() {
    this.drawLine();
  }

  drawLine() {
    //Si settano larghezza e altezza del canvas in base alla dimensione della pagina
    let canvasHtml: HTMLCanvasElement = <HTMLCanvasElement>(
      document.getElementById('canvasRef')!
    );
    const canvasRef = new ElementRef(canvasHtml);
    const canvas: HTMLCanvasElement = canvasRef.nativeElement;
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
    //-----------------------------------------------------------------------------
    //-----------------------------------------------------------------------------
    this.parents.forEach((parent: any) => {
      let parentHtml: HTMLCanvasElement = <HTMLCanvasElement>(
        document.getElementById('parent' + parent.id)!
      );
      const parentRef = new ElementRef(parentHtml);
      const parentElement = parentRef.nativeElement;
      const parentRect = parentElement.getBoundingClientRect();
      const parentCenterX =
        parentRect.left + parentRect.width / 2 - canvas.offsetLeft;
      const parentCenterY =
        parentRect.top + parentRect.height / 2 - canvas.offsetTop;
      parent.childs.forEach((childId: number) => {
        let childHtml: HTMLCanvasElement = <HTMLCanvasElement>(
          document.getElementById('child'+childId)!
        );
        const childRef = new ElementRef(childHtml);
        const childElement = childRef.nativeElement;
        const childRect = childElement.getBoundingClientRect();
        const childCenterX = childRect.left + childRect.width / 2 - canvas.offsetLeft;
        const childCenterY = childRect.top + childRect.height / 2 - canvas.offsetTop;
        const context = canvas.getContext('2d');

        if (context != null) {
          context.beginPath();
          context.moveTo(parentCenterX, parentCenterY);
          context.lineTo(childCenterX, childCenterY);
          context.strokeStyle = '#000';
          context.lineWidth = 2;
          context.stroke();
        }
      });
    });
/*
    let parentHtml: HTMLCanvasElement = <HTMLCanvasElement>(
      document.getElementById('parent1')!
    );
    let childHtml: HTMLCanvasElement = <HTMLCanvasElement>(
      document.getElementById('child6')!
    );
    const parentRef = new ElementRef(parentHtml);
    const childRef = new ElementRef(childHtml);
    const parentElement = parentRef.nativeElement;
    const childElement = childRef.nativeElement;
    const parentRect = parentElement.getBoundingClientRect();
    const childRect = childElement.getBoundingClientRect();

    const parentCenterX =
      parentRect.left + parentRect.width / 2 - canvas.offsetLeft;
    const parentCenterY =
      parentRect.top + parentRect.height / 2 - canvas.offsetTop;
    const childCenterX =
      childRect.left + childRect.width / 2 - canvas.offsetLeft;
    const childCenterY =
      childRect.top + childRect.height / 2 - canvas.offsetTop;

    const context = canvas.getContext('2d');
    if (context != null) {
      context.clearRect(0, 0, canvas.width, canvas.height);
      context.beginPath();
      context.moveTo(parentCenterX, parentCenterY);
      context.lineTo(childCenterX, childCenterY);
      context.strokeStyle = '#000';
      context.lineWidth = 2;
      context.stroke();
    }*/

  }
}
