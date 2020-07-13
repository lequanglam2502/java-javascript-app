import { Component, OnInit, Input, Output, EventEmitter, SimpleChange } from '@angular/core';
import { Item } from '../../_model/Item';
import { ItemService } from '../../_service/item.service';

@Component({
	selector: 'edit-item',
	templateUrl: './edit-item.component.html',
	styleUrls: ['./edit-item.component.css']
})
export class EditItemComponent implements OnInit {
	@Input() inItem: Item;
	@Output() outItem = new EventEmitter<Item>();

	item: Item;

	constructor(private itemService: ItemService) { }

	ngOnInit() { }

	ngOnChanges(changes: { [propKey: string]: SimpleChange }) {
		if(changes.inItem.currentValue) {
			this.item =	changes.inItem.currentValue;
		} else {
			this.item = Item.default();
		}
	}
	
	/**
		Call server to update item
		Pass result value to parent component if successful
	 */
	onSave(item: Item) {
		this.itemService.update(item).subscribe(
			data => {
				if (!data) return;
				this.outItem.emit(item);
			},
			error => {
				console.log(error);
			}
		);
		
		this.outItem.emit(this.inItem);
	}
}
