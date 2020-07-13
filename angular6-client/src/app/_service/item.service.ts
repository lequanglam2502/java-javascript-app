import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';
import { Item } from '../_model/Item';

/*
 * This service used to call CRUD entity to server
 * entity could be a real thing such as: book, employee, grocery item
 */
@Injectable()
export class ItemService {

	constructor(private http: HttpClient) { }

	public findAll(): Observable<Item[]>{
		return this.http.get<Item[]>(environment.serverUrl + environment.findAllItemUri, { responseType: 'json' });

	}

	public add(item: Item): Observable<Item> {
		return this.http.post<Item>(environment.serverUrl + environment.addItemUri, item);
	}

	public update(item: Item): Observable<Item> {
		return this.http.post<Item>(environment.serverUrl + environment.updateItemUri, item);
	}

	public delete(item: Item): Observable<boolean> {
		return this.http.post<boolean>(environment.serverUrl + environment.deleteItemUri, item);
	}
}
