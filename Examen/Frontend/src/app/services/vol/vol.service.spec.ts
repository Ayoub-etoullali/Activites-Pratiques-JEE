import { TestBed } from '@angular/core/testing';

import { VolService } from './vol.service';

describe('VolService', () => {
  let service: VolService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VolService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
