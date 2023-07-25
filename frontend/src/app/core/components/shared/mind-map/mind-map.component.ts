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
import { NavigationEnd, Router } from '@angular/router';
import { Roadmap } from 'src/app/core/models/roadmap';
import { Skill } from 'src/app/core/models/skill';
import { Step } from 'src/app/core/models/step.model';

@Component({
  selector: 'app-line-renderer',
  templateUrl: './mind-map.component.html',
  styleUrls:['./mind-map.component.css'],
})
export class MindMapComponent implements AfterViewInit, OnInit {
  @Output() viewDetails = new EventEmitter<any>();
  @Output() progressSpinnerEmitter = new EventEmitter<any>();
  @Input() set dataset(data: Roadmap) {
    data.steps!.forEach((node: Step, index: number) => {
      if (node.skills) {
        this.parents.push(node);
        if (node.skills) {
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
        }
        if (node.roadmap_links) {
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
      }
    });
    this.drawLine();
  }
  firsthalfchilds: any[] = [];
  parents: Step[] = [];
  secondhalfchilds: any[] = [];

  constructor(
    private router: Router
  ) {}

  ngOnInit(): void {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        let element = document.getElementById('canvasRef');
        element!.remove();
        element = document.getElementById('nodesContainer');
        element!.remove();
        this.progressSpinnerEmitter.emit(true);
        window.location.reload();
      }
    });
  }

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

  private drawLine() {
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
        )!.style.marginTop = this.getParentMarginTopDown(
          this.parents[index],
          this.parents[index-1]
        );
      }
    });
  }
  private disegnaLinkTraParentEChilds(
    parents: Step[],
    canvas: HTMLCanvasElement
  ) {
    parents.forEach((parent: Step, index: number) => {
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
  getParentMarginTopDown(element: Step, previusOrNext: Step): string {
    let margin: number = this.getMarginParents(element, previusOrNext);
    return margin + 'px';
  }

  getMarginParents(element: Step, previusOrNext: Step): number {
    let margin: number = 0;
    let previousChilds = 0;
    if (previusOrNext) {
      if (previusOrNext.skills) {
        previousChilds += previusOrNext.skills.length / 2;
      }
      if (previusOrNext.roadmap_links) {
        previousChilds += previusOrNext.roadmap_links.length / 2;
      }
    }
    if (previousChilds <= 2) {
      margin =
        margin +
        previousChilds *
          (document.getElementById('parent' + element.id)!.clientHeight + 10);
    } else {
      //25 è il margin top di ogni nodo laterale
      margin =
        margin +
        previousChilds *
          (document.getElementById('parent' + element.id)!.clientHeight + 25);
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

    canvas.height = this.getCanvasHeight();
    return canvas;
  }
  private getCanvasHeight():number{
    let lastNodeIndex: string = '';
    if (this.firsthalfchilds.length > this.secondhalfchilds.length) {
      lastNodeIndex =
        'childsx' + this.firsthalfchilds[this.firsthalfchilds.length - 1].id;
    } else {
      lastNodeIndex =
        'childdx' + this.secondhalfchilds[this.secondhalfchilds.length - 1].id;
    }
    let lastNodeElement: HTMLElement = <HTMLElement>(
      document.getElementById(lastNodeIndex)
    );
    let boundingRect = lastNodeElement.getBoundingClientRect();
    let distanceFromTop = boundingRect.top + window.scrollY; // Add scrollY to account for vertical scrolling
    let elementHeight = lastNodeElement.offsetHeight;
    let totalDistance = distanceFromTop + elementHeight;
    return totalDistance;
  }

  public visualizeDetail(element: any) {
    this.viewDetails.emit(element);
  }
}
