export class Item {
	public itemId: string;
	public name: string;
	public value: string;
	public date: number;

	constructor(itemId: string, name: string, value: string, date: number) {
		this.itemId = itemId;
		this.name = name;
		this.value = value;
		this.date = date;
	}

	public static default(): Item {
		return new Item("", "", "", 0);
	}
}