export class LoginResponse {
	public accessToken: string;
	public type: string;
	public username: string;
	roles: string[];

	constructor(accessToken: string, type: string, username: string, roles: string[]) {
		this.accessToken = accessToken;
		this.type = type;
		this.username = username;
		this.roles = roles;
	}
}