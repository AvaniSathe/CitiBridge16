import { Injectable } from '@angular/core';
import {HttpClient}from '@angular/common/http'
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import {UserObject} from './control-objects/UserObject'
import {User} from './control-objects/user'
import {UserWishlist} from './control-objects/UserWishlist'
import { map } from 'rxjs/operators';
import * as CryptoJS from 'crypto-js'
//import {LoginComponent} from './login/login.component'

@Injectable({
  providedIn: 'root'
})
export class UserDetailService {   
   arbitrageData:string;
   flag=0;
   userIdArray=["1","2","3","4","5"];
   authenticationData:string; 
   url:string="http://localhost:8080/rest/user/all"
   
  apiUrl = "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=cc0fd79ac4e043919c6abd69645c11d0"
   
  constructor(private httpClient:HttpClient,private router:Router) { }
  
  set (keys,value){     //password encryption
	  var key = CryptoJS.enc.Utf8.parse(keys);
	  var iv = CryptoJS.enc.Utf8.parse(keys);
	  var encrypted = CryptoJS.AES.encrypt(CryptoJS.enc.Utf8.parse(value.toString()),key,
	  {
	  	keySize : 128/8, 
	  	iv:iv,
	  	mode : CryptoJS.mode.CBC,
	  	padding : CryptoJS.pad.Pkcs7 
	  });
	  	return encrypted.toString();
  }
 
   getUsers():Observable<UserObject[]>{

    return this.httpClient.get<UserObject[]>(this.url);
  }
 
 onLogin= function () {    // navigating to stock-view after successful authentication
  this.router.navigate(['./stock-view']);
  }
  
  hasUserId(id:string):boolean{  // to check if userid exists
  for(let i=0;i<5;i++){
  if(this.userIdArray[i]===id){
  return true;
  }
  }
  return false;
  }
 
authenticate (user:UserObject): Observable<UserObject[]> {   // to authenticate user
	return this.httpClient.post("http://localhost:8080/rest/user/post",
	user,
	{responseType: 'text'}).subscribe(
	(data:any)  => {
		this.authenticationData=data;
		console.log("POST Request is successful ", this.authenticationData);
		
		if(this.hasUserId(this.authenticationData.substring(this.authenticationData.length-1))){
		  this.onLogin();
		}
	},
	error  => {
	
		console.log("Error", error);
	
		});
}
arbitrageCalculation(company:string):Observable<string>{   //arbitrage calculation
return this.httpClient.post("http://localhost:8080/rest/user/result",
	company,
	{responseType: 'text'}).subscribe(
	(data:any)  => {
	this.arbitrageData=data;
	console.log("POST Request is successful\n",this.arbitrageData);
	this.flag=1;
	},
	error  => {
	
	console.log("Error", error);
	
	});
    
}


	
addToWishlist(userWishlist:UserWishlist):Observable<string>{  // saving the stock
	return this.httpClient.post("http://localhost:8080/rest/user/wishlist",
	userWishlist,
	{responseType: 'text'}).subscribe(
	(data:any)  => {
	if(data){
	console.log("Added to wishlist successfully")
	}else{
	console.log("Error adding arbitrage to wishlist")
	}
	},
	error  => {
	
	console.log("Error", error);
	
	});
	}	
	getWishlist():Observable<UserWishlist[]>{  //getting saved stock
  this.url="http://localhost:8080/rest/user/user-wishlist";
    return this.httpClient.get<UserWishlist[]>(this.url);
  }
  
  getNews():Observable<User[]>{   // get realtime news
    return this.httpClient.get<User[]>(this.apiUrl);
  }
 onLogout= function () {   // navigating to login page
  this.router.navigate(['./login']);
  } 
  
}


