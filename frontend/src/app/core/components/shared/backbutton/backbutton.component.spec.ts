import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BackbuttonComponent } from './backbutton.component';

describe('BackbuttonComponent', () => {
  let component: BackbuttonComponent;
  let fixture: ComponentFixture<BackbuttonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BackbuttonComponent]
    });
    fixture = TestBed.createComponent(BackbuttonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
