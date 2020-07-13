import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { ItemService } from '../_service/item.service';
import { Item } from '../_model/Item';

@Component({
	selector: 'list-item',
	templateUrl: './list-item.component.html',
	styleUrls: ['./list-item.component.css']
})
export class ListItemComponent implements OnInit {
	@Output() childItem = new EventEmitter<Item>();

	itemList: Item[];

	choosedItem: Item;

	constructor(private itemService: ItemService) { }

	/** 
		Call server to get list of data
		Put it to the component global item list
	 */
	ngOnInit() {
		this.itemService.findAll().subscribe(
			data => {
				this.itemList = data;
			},
			error => {
				console.log(error);
			}
		);
	}

	/**
		After receiving add signal from dialog box
		Call server to add item
		Add to component list when receiving successful repsonse from server
	 */
	onAddConfirm(item: Item) {
		if(item) {
			this.itemList.push(item);
		}
	}

	onEditDialog(item: Item) {
		this.choosedItem = Object.assign({}, item);
	}

	/**
		After receive update successful signal from dialog box of child component
		Update item list
	 */
	onEditConfirm(item: Item) {
		let i = this.itemList.findIndex(findItem => findItem.itemId == item.itemId);
		if (i >= 0) {
			this.itemList[i] = item;
		}
	}

	onDeleteDialog(item: Item) {
		/** pass item to delete */
		this.choosedItem = Object.assign({}, item);
	}

	/**
		After receiving delete signal from dialog box
		Call server to add item
		Update list when receiving successful repsonse from server
	 */
	onDeleteConfirm(item: Item) {
		this.itemService.delete(item).subscribe(
			result => {
				if (!result) return;

				const index = this.itemList.findIndex(findItem => findItem.itemId == item.itemId);
				if (index >= 0) {
					this.itemList.splice(index, 1);
				}
			},
			error => {
				console.log(error);
			}
		);
	}
}
