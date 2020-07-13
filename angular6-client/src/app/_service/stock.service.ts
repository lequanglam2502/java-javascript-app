import { Injectable } from '@angular/core';
import { Stock } from '../_model/Stock';

import SockJS from 'sockjs-client';
import { Stomp } from 'stompjs/lib/stomp.js';

/* Service to get data from server using SockJs and Stomp */
@Injectable()
export class StockService {
	// create connection
	public stompClient;
	public stockList: Stock[];

	constructor() {
		this.initializeWebSocketConnection();
	}

	public initializeWebSocketConnection() {
		const serverUrl = 'http://localhost:8181/stock';
		const webSocket = new SockJS(serverUrl);
		this.stompClient = Stomp.over(webSocket);
		const that = this;
		this.stompClient.connect({}, function(frame) {
			that.stompClient.subscribe('/update', (data) => {
				if (data.body) {
					that.stockList = JSON.parse(data.body);
				} else {
					this.handleError("");
				}
			});
		});
	}

	public getAll() {
		this.stompClient.send('/app/get/data', {}, "Client request updated data");
	}

}
