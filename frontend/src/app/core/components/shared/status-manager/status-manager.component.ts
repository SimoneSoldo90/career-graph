import { AfterViewInit, Component, Input } from '@angular/core';

@Component({
  selector: 'app-status-manager',
  templateUrl: './status-manager.component.html',
  styleUrls: ['./status-manager.component.css']
})
export class StatusManagerComponent implements AfterViewInit{

  @Input() currentState!: string;
  @Input() showDropdown = false;
  @Input() menuTitle!: string;
  @Input() states!: any;

  onStateChanged(newState: string) {
    if (newState === 'menu') {
    } else {
      //console.log('Nuovo stato selezionato:', newState);
    }
  }

  ngAfterViewInit(): void {
    if(this.currentState){
      this.states.forEach((state:any)=>{
        if(state.title === this.currentState){
          document.getElementById("currentState")!.style.backgroundColor = state.statusColor;
          document.getElementById("currentState")!.style.color = state.color;
        }
      })
    }
  }
  onMenuOptionSelected(newState: any) {
    document.getElementById("currentState")!.style.backgroundColor = newState.statusColor;
    document.getElementById("currentState")!.style.color = newState.color;
    this.currentState = newState.title
  }
}
