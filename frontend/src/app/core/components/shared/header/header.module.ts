import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { HeaderComponent } from './header.component';
import { AvatarModule } from '../avatar/avatar.module';
import { NavmenuModule } from '../navmenu/navmenu.module';
import { NavlargemenuModule } from '../navlargemenu/navlargemenu.module';

@NgModule({
  declarations: [
    HeaderComponent
  ],
  imports: [
    CommonModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    AvatarModule,
    NavmenuModule,
    NavlargemenuModule,
  ],
  exports:[
    HeaderComponent
  ]
})
export class HeaderModule { }
