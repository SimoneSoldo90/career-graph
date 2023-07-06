import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoadmapgraphComponent } from './roadmapgraph.component';

describe('RoadmapgraphComponent', () => {
  let component: RoadmapgraphComponent;
  let fixture: ComponentFixture<RoadmapgraphComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RoadmapgraphComponent]
    });
    fixture = TestBed.createComponent(RoadmapgraphComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
