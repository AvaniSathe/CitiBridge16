import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {UserDetailService} from '../user-detail.service';
import { UserObject } from '../control-objects/UserObject';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  authenticationData:string;
  title = 'Login';
  email:string;
  password:string;
  count:number=0;
 singleUser: UserObject;
  constructor(private userData:UserDetailService,private router:Router){
  } 
  ngOnInit(){
     }
 onSubmit(){                                                  //to get the username and password
 var encryptedPassword = this.userData.set('123456$#@$^@1ERF',this.password); //password encryption
 this.singleUser={username:this.email,password:encryptedPassword}
 console.log("Email: "+this.singleUser.username);
 console.log("Password: "+this.singleUser.password);
 this.userData.authenticate(this.singleUser);
 }
  }


