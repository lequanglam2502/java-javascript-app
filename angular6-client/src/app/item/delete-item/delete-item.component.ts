import { Component, OnInit, Input, Output, EventEmitter, SimpleChange } from '@angular/core';
import { Item } from '../../_model/Item';

@Component({
	selector: 'delete-item',
	templateUrl: './delete-item.component.html',
	styleUrls: ['./delete-item.component.css']
})
export class DeleteItemComponent implements OnInit {
	@Input() inItem: Item;
	@Output() outItem = new EventEmitter<Item>();

	constructor() { }

	ngOnInit() { }

	ngOnChanges(changes: { [propKey: string]: SimpleChange }) {
		if (changes.inItem.currentValue) {
			this.inItem = changes.inItem.currentValue;
		}
	}
	/**
		Pass deleted item to parent component when receving delete confirmed
	 */
	deleteConfirm() {
		this.outItem.emit(this.inItem);
	}
}
