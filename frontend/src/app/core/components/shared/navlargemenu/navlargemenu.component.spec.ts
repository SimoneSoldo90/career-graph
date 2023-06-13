import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavlargemenuComponent } from './navlargemenu.component';

describe('NavlargemenuComponent', () => {
  let component: NavlargemenuComponent;
  let fixture: ComponentFixture<NavlargemenuComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NavlargemenuComponent]
    });
    fixture = TestBed.createComponent(NavlargemenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
