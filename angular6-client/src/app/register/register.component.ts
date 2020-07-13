import { Component, OnInit } from '@angular/core';
import { RegisterRequest } from '../_model/authentication/RegisterRequest';
import { AuthenticationService } from '../_service/authentication.service';

@Component({
	selector: 'login',
	templateUrl: './register.component.html',
	styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
	isEmail: boolean;
	isConfirmPassword: boolean;
	isRegisterSuccessful: boolean;

	registerRequest: RegisterRequest;
	errorMessage: string;

	constructor(private authenticateService: AuthenticationService) { }

	ngOnInit() {
		this.registerRequest = RegisterRequest.default();
		this.isRegisterSuccessful = false;
		this.isEmail = false;
		this.isConfirmPassword = false;
		this.errorMessage = '';
	}

	onSubmit() {
		this.authenticateService.register(this.registerRequest).subscribe(
			data => {
				this.isRegisterSuccessful = true;
			},
			error => {
				console.log(error);
				this.errorMessage = JSON.parse(error._body).message;
				this.isRegisterSuccessful = false;
			}
		);;
	}
}