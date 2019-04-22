import { Component, OnInit } from '@angular/core';
import {UserDetailService} from '../user-detail.service';
import {UserWishlist} from '../control-objects/UserWishlist';
import {Parameters} from '../control-objects/Parameters';
import { Observable } from 'rxjs';
import {HttpClient}from '@angular/common/http'
import {User} from '../control-objects/user';

@Component({
  selector: 'app-stock-view',
  templateUrl: './stock-view.component.html',
  styleUrls: ['./stock-view.component.css']
})
export class StockViewComponent implements OnInit {
 userWishList:UserWishlist[];
 NSEData:string;
 BSEData:string;
 recommendation:string;
companyName:string;
transaction:string;
singleTransaction:Parameters;
wishlistEntry:UserWishlist;
randomDate = new Date(2018,11,24);
nse:number;
bse:number;
arbitrage:string;
arbitrageNumber:number;
users:User[];
  article:[];
  results : [] ;

  constructor(private userData:UserDetailService,private httpClient:HttpClient) { 
  this.companyName="";
  }

  ngOnInit() {
  
  }
  
	onSubmit(){        //to get company name and buy/sell
	console.log(this.companyName , this.transaction);
	this.singleTransaction={companyName:this.companyName,transaction:this.transaction}
return this.httpClient.post("http://localhost:8080/rest/user/result",
	this.singleTransaction,
	{responseType: 'text'}).subscribe(
	(data:any)  => {
	if(data!="something went wrong"){
	console.log("Total Data: "+data);
	var words = data.split(' ');
     console.log(words);
     this.NSEData="NSE: "+words[2];
     this.BSEData="BSE: "+words[5];
    this.nse = Number(words[2]);
	this.bse = Number(words[5]);
	this.arbitrageNumber=this.nse-this.bse;
	this.arbitrage="Arbitrage: "+this.arbitrageNumber.toFixed(2);
     var length=words.length;
    this.recommendation=words[length-4]+" "+words[length-3]+" "+words[length-2];
	}else{
	alert("Something went wrong! \nPlease retry.");
	}
	},
	error  => {
	
	console.log("Error", error);
	
	});
	 
	}
	AddtoWishlist(){     //to save the stock 
	this.wishlistEntry={userId:0,companyName:this.companyName,arbitrage:this.nse-this.bse,quantity:0,currentMarketPrice:this.nse,transactionDate:this.randomDate,NSE:this.nse,BSE:this.bse};
	console.log(this.wishlistEntry);
	this.userData.addToWishlist(this.wishlistEntry);
	}
	onWishList(){
	this.userData.getWishlist().subscribe(data => {
      console.log("data received!!", data);
      this.userWishList = data;
      console.log(this.userWishList);
    }, err =>{
       console.log("Error in fetching data ");
    });
	}
	onNews(){       // fetch realtime news
	
     this.userData.getNews().subscribe(
       data => {
         this.users=data;
       console.log(data);
       this.article = data["articles"];
       }
       );
	
	}
	onLogout(){     // to logout
	 this.userData.onLogout();
	}
	no(){
	}
}
