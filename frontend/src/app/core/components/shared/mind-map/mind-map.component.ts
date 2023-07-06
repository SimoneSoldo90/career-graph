import {
  AfterViewInit,
  Component,
  ElementRef,
  EventEmitter,
  HostListener,
  Input,
  Output,
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
        background-color: #e5e5f7;
        opacity: 0.8;
        background-image: radial-gradient(#444cf7 0.5px, #f8f8ff 0.5px);
        background-size: 10px 10px;
      }
      td {
        text-align: center;
      }
      .button-skill {
        word-break: break-word;
        width: 100px;
        display: inline-block;
        outline: 0;
        border: 0;
        cursor: pointer;
        border-radius: 3px;
        padding: 10px 12px 10px;
        font-size: 18px;
        font-weight: lighter;
        line-height: 1;
        background-color: white;
        transition: transform 200ms, background 200ms;
        color: #000000;
        box-shadow: 0 0 0 2px #000000 inset;
        margin-top: 25px;
      }
      [id^='parent'] {
        margin-top: 75px;
      }
      .button-skill:hover {
        transform: translateY(-3px);
        box-shadow: 2px 2px 3px #144d83;
        font-weight: 400;
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
      @media screen and (min-width: 651px) and (max-width: 1200px) {
        .button-skill {
          width: 75px;
          font-size: 16px;
        }
      }
      @media screen and (min-width: 501px) and (max-width: 649px) {
        .centerTable {
          margin: 20px 25px 0px 25px;
        }
        .button-skill {
          width: 75px;
          font-size: 16px;
        }
      }
      @media screen and (min-width: 360px) and (max-width: 500px) {
        .centerTable {
          margin: 20px 25px 0px 25px;
        }
        .button-skill {
          width: 60px;
          font-size: 14px;
        }
      }
      @media screen and (min-width: 250px) and (max-width: 360px) {
        .centerTable {
          margin: 20px 15px 0px 15px;
        }
        .button-skill {
          width: 30px;
          font-size: 10px;
        }
      }
      @media screen and (max-width: 250px) {
        .centerTable {
          margin: 20px 10px 0px 10px;
        }
        .button-skill {
          width: 20px;
          font-size: 10px;
        }
      }
    `,
  ],
})
export class MindMapComponent implements AfterViewInit {
  @Output() viewDetails = new EventEmitter<any>();
  @ViewChild('scrollContainer', { static: true })
  scrollContainerRef!: ElementRef;
  @Input() set nodes(nodes: any[]) {
    nodes.forEach((node: any) => {
      if (node.childs) {
        var firsthalflength =
          node.childs.length / 2 - 1 === -1 ? 0 : node.childs.length / 2 - 1;
        this.parents.push(node);
        node.childs.forEach((child: any, index: number) => {
          let childFounded = nodes.filter((element: any) => {
            if (element.id == child) return element;
            else return null;
          });
          if (childFounded.length > 0) {
            if (index <= firsthalflength) {
              if (!this.firsthalfchilds.includes(childFounded[0])) {
                this.firsthalfchilds.push(childFounded[0]);
              }
            } else {
              if (!this.secondhalfchilds.includes(childFounded[0])) {
                this.secondhalfchilds.push(childFounded[0]);
              }
            }
          }
        });
      }
    });
    this.drawLine();
  }

  private resizeTimeout: any;
  firsthalfchilds: { id: number; title: string }[] = [];
  parents: { id: number; title: string; childs: number[] }[] = [];
  secondhalfchilds: { id: number; title: string }[] = [];

  constructor() {}

  ngAfterViewInit() {
    this.drawLine();
  }

  @HostListener('window:resize')
  onWindowResize() {
    this.drawLine();
  }

  drawLine() {
    let canvasHtml: HTMLCanvasElement = <HTMLCanvasElement>(
      document.getElementById('canvasRef')!
    );
    const canvasRef = new ElementRef(canvasHtml);
    let canvas: HTMLCanvasElement = this.getCanvas(canvasRef);
    this.disegnaLinkTraParentEChilds(this.parents, canvas);
    this.disegnaLinkTraParents(this.parents, canvas);
  }
  private disegnaLinkTraParentEChilds(
    parents: { id: number; title: string; childs: number[] }[],
    canvas: HTMLCanvasElement
  ) {
    this.parents.forEach((parent: any, index: number) => {
      let parentHtml: HTMLCanvasElement = <HTMLCanvasElement>(
        document.getElementById('parent' + parent.id)!
      );

      if (index === 0) {
        const elementHtml = document.getElementById('parent' + parent.id);
        if (elementHtml != null) {
          elementHtml.style.backgroundColor = '#144d83';
          elementHtml.style.color = 'white';
          elementHtml.style.fontWeight = '400';
        }
      } else {
        const elementHtml = document.getElementById('parent' + parent.id);
        if (elementHtml != null) {
          elementHtml.style.backgroundColor = '#acd0fb';
          elementHtml.style.color = 'black';
          elementHtml.style.fontWeight = '400';
        }
      }
      const parentRef = new ElementRef(parentHtml);
      const parentElement = parentRef.nativeElement;
      const parentRect = parentElement.getBoundingClientRect();
      const parentCenterX =
        parentRect.left + parentRect.width / 2 - canvas.offsetLeft;
      const parentCenterY =
        parentRect.top + parentRect.height / 2 - canvas.offsetTop;
      parent.childs.forEach((childId: number) => {
        let childHtml: HTMLCanvasElement | null = null;
        let childsxFounded = this.firsthalfchilds.filter((element: any) => {
          if (element.id == childId) return element;
          else return null;
        });
        let childdxFounded = this.secondhalfchilds.filter((element: any) => {
          if (element.id == childId) return element;
          else return null;
        });
        if (childsxFounded.length > 0) {
          childHtml = <HTMLCanvasElement>(
            document.getElementById('childsx' + childId)!
          );
        } else if (childdxFounded.length > 0) {
          childHtml = <HTMLCanvasElement>(
            document.getElementById('childdx' + childId)!
          );
        }

        const childRef = new ElementRef(childHtml);
        const childElement = childRef.nativeElement;
        if (childElement) {
          const childRect = childElement.getBoundingClientRect();
          const childCenterX =
            childRect.left + childRect.width / 2 - canvas.offsetLeft;
          const childCenterY =
            childRect.top + childRect.height / 2 - canvas.offsetTop;
          const context = canvas.getContext('2d');

          if (context != null) {
            context.beginPath();
            context.moveTo(parentCenterX, parentCenterY);
            context.lineTo(childCenterX, childCenterY);

            // context.quadraticCurveTo(
            //   parentCenterX,
            //   childCenterY, // Control point
            //   childCenterX,
            //   childCenterY // Destination point
            // );
            context.setLineDash([25, 5]); // Set the line dash pattern
            context.strokeStyle = '#144d83';
            context.lineWidth = 2;
            context.stroke();
          }
        }
      });
    });
  }
  private disegnaLinkTraParents(
    parents: { id: number; title: string; childs: number[] }[],
    canvas: HTMLCanvasElement
  ) {
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

      if (i < this.parents.length - 1) {
        let childHtml: HTMLCanvasElement = <HTMLCanvasElement>(
          document.getElementById('parent' + this.parents[i + 1].id)!
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
          context.lineTo(childCenterX, childCenterY);
          context.setLineDash([3, 3]); // Set the line dash pattern
          context.strokeStyle = '#14833F';
          context.lineWidth = 2;
          context.stroke();
        }
      }
    }
  }
  private getCanvas(
    canvasRef: ElementRef<HTMLCanvasElement>
  ): HTMLCanvasElement {
    let canvas = canvasRef.nativeElement;
    canvas.width = window.innerWidth;

    canvas.height = window.innerHeight * this.getMoltiplicatoreAltezza();
    return canvas;
  }
  private getMoltiplicatoreAltezza(): number {
    const rapporto: number = window.innerHeight * 0.0125;
    let moltiplicatoreAltezza = 1.3;
    if (this.firsthalfchilds.length > this.secondhalfchilds.length) {
      moltiplicatoreAltezza =
        this.firsthalfchilds.length > 10
          ? this.firsthalfchilds.length / rapporto
          : 1.3;
    } else {
      moltiplicatoreAltezza =
        this.secondhalfchilds.length > 10
          ? this.secondhalfchilds.length / rapporto
          : 1.3;
    }
    return moltiplicatoreAltezza;
  }
  public visualizeDetail(element: any,isSideNode : boolean) {
    if (element.childs && isSideNode) {
      const targetElement = document.getElementById('parent' + element.id);
      if (targetElement) {
        targetElement.scrollIntoView({ behavior: 'smooth' });
      }
    } else {
      this.viewDetails.emit(element);
    }
  }
}
