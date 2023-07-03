import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoadmapsComponent } from './roadmaps.component';

describe('RoadmapComponent', () => {
  let component: RoadmapsComponent;
  let fixture: ComponentFixture<RoadmapsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RoadmapsComponent]
    });
    fixture = TestBed.createComponent(RoadmapsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
