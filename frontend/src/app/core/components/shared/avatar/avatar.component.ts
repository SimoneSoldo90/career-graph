import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-avatar',
  templateUrl: './avatar.component.html',
  styleUrls: ['./avatar.component.css']
})
export class AvatarComponent implements OnInit {
  ngOnInit(): void {
    document.getElementById("data-initials")?.setAttribute("data-initials",this.initials)
  }
  @Input() initials!:string;
}
