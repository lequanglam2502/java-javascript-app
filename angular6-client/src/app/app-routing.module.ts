import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ListItemComponent } from './item/list-item.component';
import { StockComponent } from './component/stock/stock.component';

const routes: Routes = [
	{ path: '', redirectTo: 'item', pathMatch: 'full' },
	{ path: 'item', component: ListItemComponent },
	{ path: 'login', component: LoginComponent },
	{ path: 'login/register', component: RegisterComponent },
	{ path: 'stock', component: StockComponent }
	
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})

export class AppRoutingModule { }
