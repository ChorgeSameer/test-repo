import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BasicAuthenticationService } from '../service/basic-authentication.service';
import { UserDataService } from '../service/data/user-data.service';

export class User{
  constructor(
    public id: number,
    public firstname: string,
    public lastname: string,
    public username: string,
    public password: string,
    public emailid: string
  ){

  }
}



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = 'Sameer'
  password = ''
  errorMessage = 'Invalid Credentials'
  invalidLogin = false
  user : User

  constructor(
    private router : Router,
    private basicAuthenticationService : BasicAuthenticationService,
    private userService : UserDataService
  ) { }

  ngOnInit() {
    this.user = new User(0,'','','','','');
  }

  registerFlag : boolean = false;
  handleJWTAuthLogin() {
    this.basicAuthenticationService.executeJWTAuthenticationService(this.username, this.password)
        .subscribe(
          data => {
            console.log(data)
            this.router.navigate(['welcome', this.username])
            this.invalidLogin = false      
          },
          error => {
            console.log(error)
            this.invalidLogin = true
          }
        )
  }

  isRegister() {
    this.registerFlag = true;
  }

  registerUser(){
    this.userService.createUser(this.user)
    .subscribe(
      data => {
        console.log(data)
        this.router.navigate(['login'])
      }
    )
  }

  refresh(): void { window.location.reload(); }
}
