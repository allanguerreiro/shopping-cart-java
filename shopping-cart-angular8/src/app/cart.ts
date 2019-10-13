import { Item } from './item';
import { Customer } from './customer';

export class Cart {
    public id: string;
    public customerId: Customer['id'];
    public items: Item[] = new Array<Item>();
}