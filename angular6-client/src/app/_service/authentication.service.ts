import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { RegisterRequest } from '../_model/authentication/RegisterRequest';
import { environment } from '../../environments/environment';
import { LoginRequest } from '../_model/authentication/LoginRequest';
import { LoginResponse } from '../_model/authentication/LoginResponse';
import { Item } from '../_model/Item';


/*
 * This service used to check authentication with server
 * and return authentication data to client
 */
@Injectable()
export class AuthenticationService {

	constructor(private http: HttpClient) { }

	public login(loginRequest: LoginRequest): Observable<LoginResponse> {
		loginRequest.password = this.hashPassword(loginRequest.password);

		return this.http.post<LoginResponse>(environment.serverUrl + environment.loginUri, loginRequest);
	}

	public register(registerRequest: RegisterRequest) {
		registerRequest.password = this.hashPassword(registerRequest.password);

		return this.http.post<LoginResponse>(environment.serverUrl + environment.registerUri, registerRequest);
	}

	public logout() {
		// Remove user from local storage to log user out
		localStorage.removeItem('currentUser');
	}

	public add(item: Item): Observable<Item> {
		return this.http.post<Item>(environment.serverUrl + environment.addItemUri, item);
	}

	/* 
	   hash password before sending through internet to prevent MIM  
	   should use public key to enhance this method
	*/
	public hashPassword(password: string): string {
		return btoa(password);
	}
}
