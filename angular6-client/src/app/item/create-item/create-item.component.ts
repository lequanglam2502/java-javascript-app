import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Item } from '../../_model/Item';
import { ItemService } from '../../_service/item.service';

@Component({
	selector: 'create-item',
	templateUrl: './create-item.component.html',
	styleUrls: ['./create-item.component.css']
})
export class CreateItemComponent implements OnInit {
	@Output() outItem = new EventEmitter<Item>();

	item: Item;

	errorMessage: string;

	constructor(private itemService: ItemService) { }

	ngOnInit() {
		this.item = Item.default();
	}
	/**
		Call server to add item
		Pass result value to parent component if successful added
	 */
	onAdd(item: Item) {
		this.itemService.add(item).subscribe(
			data => {
				// add list
				this.outItem.emit(data);
				this.errorMessage = "";
				document.getElementById("createCancel").click();
			},
			error => {
				this.errorMessage = error.error.message;
			}
		);
	}
}
