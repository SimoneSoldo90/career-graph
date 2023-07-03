import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatusManagerComponent } from './status-manager.component';

describe('StatusManagerComponent', () => {
  let component: StatusManagerComponent;
  let fixture: ComponentFixture<StatusManagerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StatusManagerComponent]
    });
    fixture = TestBed.createComponent(StatusManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
