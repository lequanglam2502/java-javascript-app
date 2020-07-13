import { AppRoutingModule } from './app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';

import { httpInterceptorProviders } from './_service/authentication-interceptor';
import { HttpClientModule } from '@angular/common/http';
import { AuthenticationService } from './_service/authentication.service';
import { ItemService } from './_service/item.service';
import { TokenStorageService } from './_service/token-storage.service';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ListItemComponent } from './item/list-item.component';
import { CreateItemComponent } from './item/create-item/create-item.component';
import { EditItemComponent } from './item/edit-item/edit-item.component';
import { DeleteItemComponent } from './item/delete-item/delete-item.component';
import { StockComponent } from './component/stock/stock.component';
import { StockService } from './_service/stock.service';


@NgModule({
	declarations: [
		AppComponent,
		RegisterComponent,
		LoginComponent,
		ListItemComponent,
		CreateItemComponent,
		EditItemComponent,
		DeleteItemComponent,
		StockComponent
	],
	imports: [
		HttpClientModule,
		BrowserModule,
		FormsModule,
		HttpModule,
		AppRoutingModule
	],
	providers: [
		httpInterceptorProviders,
		AuthenticationService,
		TokenStorageService,
		ItemService,
		StockService
	],
	bootstrap: [AppComponent]
})

export class AppModule { }
