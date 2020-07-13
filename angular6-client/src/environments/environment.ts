// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
	production: false,
	defaultUser: 'colin',
	serverUrl: 'https://localhost:8181',
	registerUri: '/auth/register',
	loginUri: '/auth/login',
	findAllItemUri: '/secure/api/item/findAll',
	addItemUri: '/secure/api/item/add',
	updateItemUri: '/secure/api/item/update',
	deleteItemUri: '/secure/api/item/delete'
};
