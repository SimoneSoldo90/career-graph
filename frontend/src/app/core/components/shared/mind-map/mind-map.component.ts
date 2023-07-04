import {
  AfterViewInit,
  Component,
  ElementRef,
  HostListener,
  OnInit,
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
        z-index:-1;

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
        padding: 14px 24px 16px;
        font-size: 18px;
        font-weight: 700;
        line-height: 1;
        background-color:white;
        transition: transform 200ms, background 200ms;
        color: #000000;
        box-shadow: 0 0 0 3px #000000 inset;
      }
      .button-skill:hover {
        transform: translateY(-2px);
      }
    `,
  ],
})
export class MindMapComponent implements AfterViewInit {
  @ViewChild('canvasRef') canvasRef!: ElementRef;
  @ViewChild('parent') parentRef!: ElementRef;
  @ViewChild('child') childRef!: ElementRef;

  ngAfterViewInit() {
    this.drawLine();
  }

  @HostListener('window:resize')
  onWindowResize() {
    this.drawLine();
  }

  drawLine() {
    const canvas = this.canvasRef.nativeElement;

    canvas.width  = window.innerWidth;
    canvas.height = window.innerHeight;
    const parentElement = this.parentRef.nativeElement;
    const childElement = this.childRef.nativeElement;
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
    context.clearRect(0, 0, canvas.width, canvas.height);
    context.beginPath();
    context.moveTo(parentCenterX, parentCenterY);
    context.lineTo(childCenterX, childCenterY);
    context.strokeStyle = '#000';
    context.lineWidth = 2;
    context.stroke();
  }
}
