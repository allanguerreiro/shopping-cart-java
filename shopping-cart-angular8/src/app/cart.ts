import { Item } from './item';
import { Customer } from './customer';

export class Cart {
    public id: string;
    public idCustomer: Customer['id'];
    public items: Item[] = new Array<Item>();
}