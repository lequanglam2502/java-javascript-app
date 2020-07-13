export class Stock {
	public stockId: string;
	public symbol: string;
	public price: string;
	public trend: string;

	constructor(stockId: string, symbol: string, price: string, trend: string) {
		this.stockId = stockId;
		this.symbol = symbol;
		this.price = price;
		this.trend = trend;
	}

	public static default(): Stock {
		return new Stock("", "", "", "");
	}
}
