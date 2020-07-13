import { Component, OnInit } from '@angular/core';
import { StockService } from '../../_service/stock.service';
import { Stock } from '../../_model/Stock'

@Component({
	selector: 'stock',
	templateUrl: './stock.component.html',
	styleUrls: ['./stock.component.css']
})
export class StockComponent implements OnInit {
	stockList: Stock[];

	constructor(private stockService: StockService) { }

	/**
		Call server to get list of data
		Put it to the component global item list
	 */
	ngOnInit() {
		setInterval(() => {
			this.pingData();
		}, 5000);
	}

	public pingData() {
		this.stockService.getAll();
		this.stockList = this.stockService.stockList;
	}
}
