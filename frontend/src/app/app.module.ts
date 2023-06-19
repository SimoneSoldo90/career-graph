import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './core/components/shared/footer/footer.component';
import { LoginComponent } from './core/components/pages/login/login.component';
import { HeaderModule } from './core/components/shared/header/header.module';
import { MenteesComponent } from './core/components/pages/mentees/mentees.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RoadmapsModule } from './core/components/pages/roadmaps/roadmaps.module';
import { SkillsModule } from './core/components/pages/skills/skills.module';
import { HttpClientModule } from '@angular/common/http';
import { FormPageModule } from './core/components/pages/form-page/form-page.module';
import { MatSidenavModule } from '@angular/material/sidenav';


@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    LoginComponent,
    MenteesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HeaderModule,
    BrowserAnimationsModule,
    RoadmapsModule,
    SkillsModule,
    HttpClientModule,
    FormPageModule,
    MatSidenavModule,
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
