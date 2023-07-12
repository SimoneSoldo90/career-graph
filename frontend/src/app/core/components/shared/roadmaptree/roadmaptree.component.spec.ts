import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoadmaptreeComponent } from './roadmaptree.component';

describe('RoadmaptreeComponent', () => {
  let component: RoadmaptreeComponent;
  let fixture: ComponentFixture<RoadmaptreeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RoadmaptreeComponent]
    });
    fixture = TestBed.createComponent(RoadmaptreeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
