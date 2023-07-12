import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GraforoadmapComponent } from './graforoadmap.component';

describe('GraforoadmapComponent', () => {
  let component: GraforoadmapComponent;
  let fixture: ComponentFixture<GraforoadmapComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GraforoadmapComponent]
    });
    fixture = TestBed.createComponent(GraforoadmapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
