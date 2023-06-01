import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit{
  ngOnInit(): void {
    this.userinitials = this.getInitials(localStorage.getItem("userFullName")||'')
  }
  userinitials:string="SS";
  getInitials(userFullName:string){
    let rgx = new RegExp(/(\p{L}{1})\p{L}+/, 'gu');
    let initials = [...userFullName.matchAll(rgx)] || [];
    return ((initials.shift()?.[1] || '') + (initials.pop()?.[1] || ''));
  }
}
