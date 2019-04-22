import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StockViewComponent } from './stock-view/stock-view.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [{path: '',component:LoginComponent},{path: 'stock-view' , component: StockViewComponent},
						{path: 'stock-view',component:StockViewComponent},{path: 'login' , component: LoginComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})


export class AppRoutingModule { }
