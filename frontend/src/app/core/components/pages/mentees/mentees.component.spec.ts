import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenteesComponent } from './mentees.component';

describe('MenteesComponent', () => {
  let component: MenteesComponent;
  let fixture: ComponentFixture<MenteesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MenteesComponent]
    });
    fixture = TestBed.createComponent(MenteesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
