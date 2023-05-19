import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthenticationService} from "../services/authentication/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userFormGroup!: FormGroup;
  errorMessage! : string;

  constructor(private formBuilder: FormBuilder,
              private authenticationService: AuthenticationService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.userFormGroup = this.formBuilder.group({
      username: this.formBuilder.control(""),
      password: this.formBuilder.control("")
    })
  }

  handleLogin() {
    let username = this.userFormGroup.value.username;
    let password = this.userFormGroup.value.password;
    this.authenticationService.login(username, password).subscribe({
      next: (appUser) => {
        this.authenticationService.authenticateUser(appUser).subscribe({
          next: (data) => {
            this.router.navigateByUrl("/admin/products"); //user
          }
        });
      },
      error: (err) => {
        this.errorMessage=err;
      }
    })
  }
}
