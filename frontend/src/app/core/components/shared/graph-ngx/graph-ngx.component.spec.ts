import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GraphNgxComponent } from './graph-ngx.component';

describe('GraphNgxComponent', () => {
  let component: GraphNgxComponent;
  let fixture: ComponentFixture<GraphNgxComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GraphNgxComponent]
    });
    fixture = TestBed.createComponent(GraphNgxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
