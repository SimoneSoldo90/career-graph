import { TestBed } from '@angular/core/testing';

import { MenteeService } from './mentee.service';

describe('MenteeService', () => {
  let service: MenteeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MenteeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
