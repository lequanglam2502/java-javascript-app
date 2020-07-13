import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './_service/token-storage.service';
import { Router } from '@angular/router';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
	title = 'app';

	public username;
	private roles: string[];
	public role: string;

	constructor(private tokenStorage: TokenStorageService, private route: Router) { }

	ngOnInit() {
		if (this.tokenStorage.getToken()) {
			this.username = this.tokenStorage.getUsername();
			this.roles = this.tokenStorage.getRoles();
			this.roles.every(role => {
				if (role === 'ROLE_ADMIN') {
					this.role = 'admin';
					return false;
				} else if (role === 'ROLE_USER') {
					this.role = 'USER';
					return false;
				}
				this.role = 'user';
				return true;

			});
		} else {
			this.route.navigate(["/login"])
		}
	}

	logout() {
		this.tokenStorage.logout();
	}
}

export class style { }
