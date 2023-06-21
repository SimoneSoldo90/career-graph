import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoadmapComponent } from './roadmap.component';

describe('RoadmapComponent', () => {
  let component: RoadmapComponent;
  let fixture: ComponentFixture<RoadmapComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RoadmapComponent]
    });
    fixture = TestBed.createComponent(RoadmapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
