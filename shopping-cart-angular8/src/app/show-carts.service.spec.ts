import { TestBed } from '@angular/core/testing';

import { ShowCartsService } from './show-carts.service';

describe('ShowCartsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ShowCartsService = TestBed.get(ShowCartsService);
    expect(service).toBeTruthy();
  });
});
