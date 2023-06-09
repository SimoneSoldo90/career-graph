import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './core/components/shared/footer/footer.component';
import { LoginComponent } from './core/components/pages/login/login.component';
import { HeaderModule } from './core/components/shared/header/header.module';
import { SkillsComponent } from './core/components/pages/skills/skills.component';
import { MenteesComponent } from './core/components/pages/mentees/mentees.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormPageComponent } from './core/components/pages/form-page/form-page.component';
import { RoadmapsComponent } from './core/components/pages/roadmaps/roadmaps.component';
import { FormPageModule } from './core/components/pages/form-page/form-page.module';


@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    LoginComponent,
    SkillsComponent,
    MenteesComponent,
    RoadmapsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HeaderModule,
    BrowserAnimationsModule,
    FormPageModule,
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
