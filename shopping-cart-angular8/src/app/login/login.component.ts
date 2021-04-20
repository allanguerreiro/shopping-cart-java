import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { User } from '../models';

import { AuthenticationService } from '../services';

@Component({ templateUrl: 'login.component.html' })
export class LoginComponent implements OnInit {
  user: User = new User();
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    // redirect to home if already logged in
    if (this.authenticationService.currentUserValue) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  newCustomer(): void {
    this.submitted = false;
    this.user = new User();
  }

  onSubmit() {
    this.submitted = true;

    this.loading = true;
    this.authenticationService.login(this.user)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate([this.returnUrl]);
        },
        error => {
          this.error = error;
          this.loading = false;
        });
  }
}
