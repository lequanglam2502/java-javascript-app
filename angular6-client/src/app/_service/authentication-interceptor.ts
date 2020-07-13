import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';

import { TokenStorageService } from './token-storage.service'

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {

	constructor(private token: TokenStorageService) { }

	intercept(req: HttpRequest<any>, next: HttpHandler) {
		const token = this.token.getToken();
		let authReq = req;
		if (token != null) {
			authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
		} else {
			authReq = req.clone({ headers: req.headers.set('Content-Type', 'application/json') });
		}
		return next.handle(authReq);
	}
}

export const httpInterceptorProviders = [
	{ provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true }
];