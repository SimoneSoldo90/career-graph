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
import { Roadmap } from 'src/app/core/models/roadmap';
import { Skill } from 'src/app/core/models/skill';
import { Step } from 'src/app/core/models/step.model';

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
        margin-top: 25px;
      }
      [id^='childdxroadmap'],
      [id^='childsxroadmap'],
      [id^='parent'] {
        background-color: #144d83;
        color: white;
        font-weight: 400;
      }
      .button-skill:hover {
        transform: translateY(-3px);
        box-shadow: 0 0 0 3px #000000 inset;
        font-weight: 500 !important;
      }
      #tablecontainer {
        margin: 0 auto;
      }
      .leftTable {
        float: left;
        position: relative;
      }
      .centerTable {
        float: left;
        position: relative;
        margin: 20px 200px 0px 200px;
      }
      .rightTable {
        float: left;
        position: relative;
      }
      @media screen and (min-width: 801px) and (max-width: 900px) {
        .centerTable {
          margin: 20px 150px 0px 150px;
        }
      }
      @media screen and (min-width: 651px) and (max-width: 800px) {
        .centerTable {
          margin: 20px 75px 0px 75px;
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
export class MindMapComponent implements AfterViewInit, OnInit {
  @Output() viewDetails = new EventEmitter<any>();
  @Input() set dataset(data: Roadmap) {
    data.steps!.forEach((node: Step) => {
      if (node.skills) {
        this.parents.push(node);

        node.skills.forEach((child: Skill, index: number) => {
          let skillsToPush: any = {
            id: 'skill' + child.id,
            id_db: child.id,
            title: child.title,
            description: child.description,
          };
          if (index % 2 === 0) {
            this.firsthalfchilds.push(skillsToPush);
          } else {
            this.secondhalfchilds.push(skillsToPush);
          }
        });

        node.roadmap_links.forEach((roadmap: any, index: number) => {
          let roadmapToPush: any = {
            id: 'roadmap' + roadmap.roadmap_id,
            id_db: roadmap.roadmap_id,
            title: roadmap.roadmap_title,
            description: roadmap.roadmap_description,
          };
          if (index % 2 === 0) {
            this.firsthalfchilds.push(roadmapToPush);
          } else {
            this.secondhalfchilds.push(roadmapToPush);
          }
        });
      }
    });
    this.drawLine();
  }
  firsthalfchilds: any[] = [];
  parents: Step[] = [];
  secondhalfchilds: any[] = [];

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
    this.disegnaLinkTraParentEChilds(this.parents, canvas);
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
    parents: Step[],
    canvas: HTMLCanvasElement
  ) {
    this.parents.forEach((parent: Step, index: number) => {
      let parentHtml: HTMLCanvasElement = <HTMLCanvasElement>(
        document.getElementById('parent' + parent.id)!
      );

      if (index !== 0) {
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
      parent.roadmap_links.forEach((link: any, index: number) => {
        let roadmapChild: any = {
          id: 'roadmap' + link.roadmap_id,
          title: link.roadmap_title,
          description: link.roadmap_description,
        };
        let childHtml: HTMLCanvasElement | null = null;
        let childsxFounded = this.firsthalfchilds.filter((element: any) => {
          if (element.id == roadmapChild.id) return element;
          else return null;
        });
        let childdxFounded = this.secondhalfchilds.filter((element: any) => {
          if (element.id == roadmapChild.id) return element;
          else return null;
        });
        if (childsxFounded.length > 0) {
          childHtml = <HTMLCanvasElement>(
            document.getElementById('childsx' + roadmapChild.id)!
          );
        } else if (childdxFounded.length > 0) {
          childHtml = <HTMLCanvasElement>(
            document.getElementById('childdx' + roadmapChild.id)!
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
      parent.skills.forEach((child: Skill) => {
        let roadmapChild: any = {
          id: 'skill' + child.id,
          id_db: child.id,
          title: child.title,
          description: child.description,
        };
        let childHtml: HTMLCanvasElement | null = null;
        let childsxFounded = this.firsthalfchilds.filter((element: any) => {
          if (element.id == roadmapChild.id) return element;
          else return null;
        });
        let childdxFounded = this.secondhalfchilds.filter((element: any) => {
          if (element.id == roadmapChild.id) return element;
          else return null;
        });
        if (childsxFounded.length > 0) {
          childHtml = <HTMLCanvasElement>(
            document.getElementById('childsx' + roadmapChild.id)!
          );
        } else if (childdxFounded.length > 0) {
          childHtml = <HTMLCanvasElement>(
            document.getElementById('childdx' + roadmapChild.id)!
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
  private disegnaLinkTraParents(parents: Step[], canvas: HTMLCanvasElement) {
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
  getParentMarginTopDown(arg0: Step): string {
    let margin: number = this.getMarginParents(arg0);
    return margin + 'px';
  }

  getMarginParents(arg0: Step): number {
    let margin: number = 0;
    if (arg0.skills) {
      margin =
        (arg0.skills.length / 2) *
        (document.getElementById('parent' + arg0.id)!.clientHeight / 1.7);
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
    // let moltiplicatoreAltezza = 1.9;
    let moltiplicatoreAltezza = 1.3;
    if (this.firsthalfchilds.length > this.secondhalfchilds.length) {
      moltiplicatoreAltezza = this.firsthalfchilds.length / rapporto;
      // this.firsthalfchilds.length > 10
      //   ? this.firsthalfchilds.length / rapporto
      //   : moltiplicatoreAltezza;
    } else {
      moltiplicatoreAltezza = this.secondhalfchilds.length / rapporto;
      // this.secondhalfchilds.length > 10
      //   ? this.secondhalfchilds.length / rapporto
      //   : moltiplicatoreAltezza;
    }
    return moltiplicatoreAltezza;
  }
  public visualizeDetail(element: any, isSideNode: boolean) {
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
