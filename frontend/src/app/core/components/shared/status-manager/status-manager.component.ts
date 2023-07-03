import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-status-manager',
  templateUrl: './status-manager.component.html',
  styleUrls: ['./status-manager.component.css']
})
export class StatusManagerComponent {
  @Input() currentState!: string;
  @Input() allStates!: string[];
  @Input() showDropdown = false;
  @Input() menuTitle!: string;

  onStateChanged(newState: string) {
    if (newState === 'menu') {
    } else {
      //console.log('Nuovo stato selezionato:', newState);
    }
  }

  onMenuOptionSelected(newState: string) {
    this.currentState = newState
    //console.log('Nuovo stato selezionato:', newState);
  }
}
