import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './core/components/shared/footer/footer.component';
import { LoginComponent } from './core/components/pages/login/login.component';
import { HeaderModule } from './core/components/shared/header/header.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RoadmapsModule } from './core/components/pages/roadmaps/roadmaps.module';
import { SkillsModule } from './core/components/pages/skills/skills.module';
import { MenteesModule } from './core/components/pages/mentees/mentees.module';
import { HttpClientModule } from '@angular/common/http';
import { FormPageModule } from './core/components/pages/form-page/form-page.module';
import { MatSidenavModule } from '@angular/material/sidenav';
import { RoadmapModule } from './core/components/pages/roadmap/roadmap.module';
import { StatusManagerModule } from './core/components/shared/status-manager/status-manager.module';
import { DrawerModule } from './core/components/shared/drawer/drawer.module';
import { RoadmapgraphModule } from './core/components/pages/roadmapgraph/roadmapgraph.module';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HeaderModule,
    BrowserAnimationsModule,
    RoadmapsModule,
    RoadmapModule,
    SkillsModule,
    MenteesModule,
    HttpClientModule,
    FormPageModule,
    MatSidenavModule,
    StatusManagerModule,
    DrawerModule,
    RoadmapgraphModule,
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
