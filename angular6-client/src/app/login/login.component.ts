import { Component, OnInit } from '@angular/core';

import { environment } from '../../environments/environment';
import { AuthenticationService } from '../_service/authentication.service';
import { TokenStorageService } from '../_service/token-storage.service';
import { LoginRequest } from '../_model/authentication/LoginRequest';

@Component({
	selector: 'login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
	id: number;
	loginRequest: LoginRequest;
	returnUrl: string;
	isCookie: boolean;

	constructor(private authenticateService: AuthenticationService,
		private tokenStorageService: TokenStorageService) { }

	ngOnInit() {
		/* will implement Spring security remember me and cookie manager later */
		this.isCookie = false;
		this.id;
		this.loginRequest = new LoginRequest(environment.defaultUser, environment.defaultUser);
	}

	onSubmit() {
		this.authenticateService.login(this.loginRequest).subscribe(
			data => {
				/* save token to storage */
				this.tokenStorageService.saveToken(data.accessToken);
				this.tokenStorageService.saveUsername(data.username);
				this.tokenStorageService.saveRoles(data.roles);
				this.reloadPage();
			},
			error => {
				console.log(error);
				/* reset user info*/
				this.loginRequest = LoginRequest.default();
			}
		);
	}

	reloadPage() {
		/* trigger reload, rerender page once only */
		document.getElementById("appTitleId").click();
	}
}