import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StockViewComponent } from './stock-view/stock-view.component'
import { LoginComponent } from './login/login.component';
import {HttpClientModule} from '@angular/common/http';
import {UserDetailService} from './user-detail.service';

@NgModule({
  declarations: [
    AppComponent,
    StockViewComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    
  ],
  providers: [UserDetailService],
  bootstrap: [AppComponent]
})
export class AppModule { }
