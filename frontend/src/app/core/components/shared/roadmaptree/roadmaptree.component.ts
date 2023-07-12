import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  ElementRef,
  EventEmitter,
  HostListener,
  Input,
  OnInit,
  Output,
} from '@angular/core';

@Component({
  selector: 'app-roadmaptree',
  templateUrl: './roadmaptree.component.html',
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
        margin: 0 25px;
      }
      .button-skill {
        word-break: break-word;
        width: 120px;
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
      }

      .button-skill:hover {
        transform: translateY(-3px);
        box-shadow: 0 0 0 3px #000000 inset;
        font-weight: 500;
      }
      #tablecontainer {
        margin: 0 auto;
      }
      table {
        border-spacing: 0 30px;
      }
      .leftTable {
        float: left;
        position: relative;
      }
      .centerTable {
        float: left;
        position: relative;
        margin: 20px 150px 0px 0px;
      }
      .rightTable {
        float: left;
        position: relative;
      }
      @media screen and (min-width: 801px) and (max-width: 900px) {
        .centerTable {
          margin: 20px 150px 0px 0px;
        }
      }
      @media screen and (min-width: 651px) and (max-width: 800px) {
        .centerTable {
          margin: 20px 75px 0px 0px;
        }
      }
      @media screen and (min-width: 501px) and (max-width: 649px) {
        .centerTable {
          margin: 20px 25px 0px 0px;
        }
        .button-skill {
          width: 80px;
          font-size: 16px;
        }
      }
      @media screen and (min-width: 360px) and (max-width: 500px) {
        .centerTable {
          margin: 20px 25px 0px 0px;
        }
        .button-skill {
          width: 70px;
          font-size: 14px;
        }
      }
      @media screen and (min-width: 250px) and (max-width: 360px) {
        .centerTable {
          margin: 20px 15px 0px 0px;
        }
        .button-skill {
          width: 60px;
          font-size: 10px;
        }
      }
      @media screen and (max-width: 250px) {
        .centerTable {
          margin: 20px 10px 0px 0px;
        }
        .button-skill {
          width: 50px;
          font-size: 10px;
        }
      }
      .buttonFollowPath:hover {
        animation: shake 1.00s;
      }

      @keyframes shake {
        10%,
        50% {
          transform: translate3d(0, 0, 0);
        }
        10%,
        60% {
          transform: translate3d(3px, 0, 0);
        }
        10%,
        30%,
        50% {
          transform: translate3d(-4px, 0, 0);
        }
        20%,
        30% {
          transform: translate3d(6px, 0, 0);
        }
      }
    `,
  ],
})
export class RoadmaptreeComponent implements AfterViewInit, OnInit {
  @Output() viewDetails = new EventEmitter<any>();
  @Input() set nodes(nodes: any[]) {
    nodes.forEach((node: any) => {
      if (node.parent) {
        this.parents.push(node);
        node.childs.forEach((child: any, index: number) => {
          let childFounded = nodes.filter((element: any) => {
            if (element.id == child.id) return element;
            else return null;
          });
          if (childFounded.length > 0) {
              if (!this.seconlevelchilds.includes(childFounded[0])) {
                this.seconlevelchilds.push(childFounded[0]);
              }
          }
        });
      }
      this.seconlevelchilds.forEach((child,index)=>{
        if(child.childs){
          if(!this.secondLevelParents.includes(child)){
            this.secondLevelParents.push(child)
          }
          child.childs.forEach((element,index)=>{
            if(!this.thirdlevelchilds.includes(element)){
              this.thirdlevelchilds.push(element)
            }
          })

        }
      })
    });
    this.drawLine();
  }
  firsthalfchilds: { id: number; title: string }[] = [];
  parents: { id: number; title: string; childs: number[] }[] = [];
  secondLevelParents: { id: number; title: string; childs: number[] }[] = [];
  seconlevelchilds: { id: number; title: string ; childs: any[] }[] = [];
  thirdlevelchilds: { id: number; title: string ; childs: any[] }[] = [];


  constructor(private changeDetector: ChangeDetectorRef) {}
  ngOnInit(): void {}

  ngAfterViewInit() {
    this.drawLine();
  }

  @HostListener('window:resize', ['$event'])
  onWindowResize(event: Event) {
    //RIDISEGNA IL TUTTO AL RESIZE DELLA PAGINA
    this.drawLine();
  }

  @HostListener('window:beforeunload')
  onBeforeUnload() {
    //VIENE EFFETTUATO LO SCROLL ALL'ELEMENTO canvasRef CHE È POSIZIONATO ALLO 0,0 DELLA PAGINA
    //QUESTO SERVE A FAR SI CHE AL REFRESH DELLA PAGINA IL DISEGNO SUL CANVA VENGA EFFETTUATO CORRETTAMENTE IN BASE ALLA POSIZIONE DEI NODI DEL GRAFO
    document.getElementById('canvasRef')?.scrollIntoView();
  }

  drawLine() {
    document.getElementById('canvasRef')?.scrollIntoView();

    let canvas: HTMLCanvasElement = this.getCanvas();
    this.adjustParentsMargins();
    this.disegnaLinkTraParents(this.parents, canvas);
    this.disegnaLinkTraParentEChilds(this.parents, canvas,'parent','secLevel');
    this.disegnaLinkTraParentEChilds(this.secondLevelParents, canvas,'secLevel','thirdLevel');
  }
  adjustParentsMargins() {
    this.parents.forEach((parent, index) => {
      if (document.getElementById('parent' + this.parents[index].id)) {
        document.getElementById(
          'parent' + this.parents[index].id
        )!.style.marginTop = this.getParentMarginTopDown(this.parents[index]);
        document.getElementById(
          'parent' + this.parents[index].id
        )!.style.marginBottom = this.getParentMarginTopDown(
          this.parents[index]
        );
      }
    });
  }
  private disegnaLinkTraParentEChilds(
    parents: { id: number; title: string; childs: number[] }[],
    canvas: HTMLCanvasElement,
    parentPrefix: string,
    childPrefix: string
  ) {
    console.log(parents)
    console.log(parentPrefix)
    console.log(childPrefix)

    parents.forEach((parent: any, index: number) => {
      let parentHtml: HTMLCanvasElement = <HTMLCanvasElement>(
        document.getElementById(parentPrefix + parent.id)!
      );

      if (index === 0) {
        const elementHtml = document.getElementById(parentPrefix + parent.id);
        if (elementHtml != null) {
          elementHtml.style.backgroundColor = '#144d83';
          elementHtml.style.color = 'white';
          elementHtml.style.fontWeight = '400';
        }
      } else {
        const elementHtml = document.getElementById(parentPrefix + parent.id);
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
      parent.childs.forEach((childId: any) => {
        let childHtml: HTMLCanvasElement | null = null;
        childHtml = <HTMLCanvasElement>(
          document.getElementById(childPrefix + childId.id)!
        );
        const childRef = new ElementRef(childHtml);
        const childElement = childRef.nativeElement;
        console.log(childPrefix + childId)
        console.log(childElement)
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
            // context.lineTo(childCenterX, childCenterY);
            //MODIFICARE GLI OFFSET PER CURVARE LE LINEE
            context.bezierCurveTo(
              parentCenterX + 0,
              parentCenterY + 0,
              childCenterX - 0,
              childCenterY - 0,
              childCenterX,
              childCenterY
            );
            context.setLineDash([2, 2]);
            context.setTransform;
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
    for (let i = 0; i < parents.length; i++) {
      let parentHtml: HTMLCanvasElement = <HTMLCanvasElement>(
        document.getElementById('parent' + parents[i].id)!
      );
      const parentRef = new ElementRef(parentHtml);
      const parentElement = parentRef.nativeElement;
      const parentRect = parentElement.getBoundingClientRect();
      const parentCenterX =
        parentRect.left + parentRect.width / 2 - canvas.offsetLeft;
      const parentCenterY =
        parentRect.top + parentRect.height / 2 - canvas.offsetTop;

      if (i < parents.length - 1) {
        let childHtml: HTMLCanvasElement = <HTMLCanvasElement>(
          document.getElementById('parent' + parents[i + 1].id)!
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
          // context.lineTo(childCenterX, childCenterY);
          context.bezierCurveTo(
            parentCenterX + 0,
            parentCenterY + 0,
            childCenterX - 0,
            childCenterY - 0,
            childCenterX,
            childCenterY
          );
          context.setLineDash([0, 0]); // Set the line dash pattern
          context.strokeStyle = '#00b894';
          context.lineWidth = 5;
          context.stroke();
        }
      }
    }
  }
  getParentMarginTopDown(arg0: {
    id: number;
    title: string;
    childs: number[];
  }): string {
    let margin: number = this.getMarginParents(arg0);
    return margin + 'px';
  }

  getMarginParents(arg0: {
    id: number;
    title: string;
    childs: number[];
  }): number {
    let margin: number = 0;
    if (arg0.childs) {
      margin =
        (arg0.childs.length / 2) *
        (document.getElementById('parent' + arg0.id)!.clientHeight / 1.4);
    }
    return margin;
  }

  private getCanvas(): HTMLCanvasElement {
    let canvasHtml: HTMLCanvasElement = <HTMLCanvasElement>(
      document.getElementById('canvasRef')!
    );
    const canvasRef = new ElementRef(canvasHtml);
    let canvas = canvasRef.nativeElement;
    canvas.width = window.innerWidth;

    canvas.height = window.innerHeight * this.getMoltiplicatoreAltezza();
    return canvas;
  }
  private getMoltiplicatoreAltezza(): number {
    // const rapporto: number = window.innerHeight * 0.0125;
    const rapporto: number = window.innerHeight * 0.01;
    let moltiplicatoreAltezza = 1.4;
    if (this.firsthalfchilds.length > this.seconlevelchilds.length) {
      moltiplicatoreAltezza =
        this.firsthalfchilds.length > 10
          ? this.firsthalfchilds.length / rapporto
          : moltiplicatoreAltezza;
    } else {
      moltiplicatoreAltezza =
        this.seconlevelchilds.length > 10
          ? this.seconlevelchilds.length / rapporto
          : moltiplicatoreAltezza;
    }
    return moltiplicatoreAltezza;
  }
  public visualizeDetail(element: any, isSideNode: boolean) {
    if (element.parent && isSideNode) {
      const targetElement = document.getElementById('parent' + element.id);
      if (targetElement) {
        targetElement.scrollIntoView({ behavior: 'smooth' });
      }
    } else {
      this.viewDetails.emit(element);
    }
  }
}
