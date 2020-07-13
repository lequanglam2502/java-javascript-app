export class RegisterRequest {
	public username: string;
	public password: string;
	public confirmPassword: string;
	public email: string;
	public role: string[];

	constructor(username: string, password: string, confirmPassword: string, email: string, role: string[]) {
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
		this.role = role;
	}

	public static default(): RegisterRequest {
		return new RegisterRequest("", "", "", "", ['ROLE_USER']);
	}
}