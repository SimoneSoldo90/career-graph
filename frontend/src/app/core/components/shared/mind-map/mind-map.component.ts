import {
  AfterViewInit,
  Component,
  ElementRef,
  EventEmitter,
  HostListener,
  OnInit,
  Output,
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
        border-radius: 5px;
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
      [id^="parent"] {
        margin-top: 75px;
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
export class MindMapComponent implements AfterViewInit, OnInit {
  @Output() viewDetails = new EventEmitter<any>();

  firsthalfchilds: { id: number; title: string }[] = [];
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

  ngOnInit(){
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
          document.getElementById('child' + childId)!
        );
        const childRef = new ElementRef(childHtml);
        const childElement = childRef.nativeElement;
        const childRect = childElement.getBoundingClientRect();
        const childCenterX =
          childRect.left + childRect.width / 2 - canvas.offsetLeft;
        const childCenterY =
          childRect.top + childRect.height / 2 - canvas.offsetTop;
        const context = canvas.getContext('2d');

        if (context != null) {
          context.beginPath();
          context.moveTo(parentCenterX, parentCenterY);
          context.quadraticCurveTo(
            parentCenterX,
            childCenterY, // Control point
            childCenterX,
            childCenterY // Destination point
          );
          context.setLineDash([15, 5]); // Set the line dash pattern
          context.strokeStyle = '#144d83';
          context.lineWidth = 2;
          context.stroke();
        }
      });
    });
    for (let i = 0; i < this.parents.length; i++) {
      let parentHtml: HTMLCanvasElement = <HTMLCanvasElement>(
        document.getElementById('parent' + this.parents[i].id)!
      );
      const parentRef = new ElementRef(parentHtml);
      const parentElement = parentRef.nativeElement;
      const parentRect = parentElement.getBoundingClientRect();
      const parentCenterX =
        parentRect.left + parentRect.width / 2 - canvas.offsetLeft;
      const parentCenterY =
        parentRect.top + parentRect.height / 2 - canvas.offsetTop;
        console.log("i = "+i)
        console.log("(this.parents.length - 1) =  "+(this.parents.length - 1))
      if (i < (this.parents.length-1)) {
        let childHtml: HTMLCanvasElement = <HTMLCanvasElement>(
          document.getElementById('parent' + (this.parents[i+1].id))!
        );
        console.log('parent' + (this.parents[i].id))
        const childRef = new ElementRef(childHtml);
        const childElement = childRef.nativeElement;
        const childRect = childElement.getBoundingClientRect();
        const childCenterX =
          childRect.left + childRect.width / 2 - canvas.offsetLeft;
        const childCenterY =
          childRect.top + childRect.height / 2 - canvas.offsetTop;
        const context = canvas.getContext('2d');

        if (context != null) {
          console.log("Parent "+this.parents[i])
          console.log("Coordinates "+parentCenterX+parentCenterY)
          console.log("Coordinates Child "+childCenterX+childCenterY)
          context.beginPath();
          context.moveTo(parentCenterX, parentCenterY);
          context.lineTo(childCenterX, childCenterY);
          context.setLineDash([5, 5]); // Set the line dash pattern
          context.strokeStyle = '#14833F';
          context.lineWidth = 2;
          context.stroke();
        }
      }
    }
  }

  visualizeDetail(element: any) {
    this.viewDetails.emit(element);
  }
}
