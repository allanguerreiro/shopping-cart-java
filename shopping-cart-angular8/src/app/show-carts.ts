import { Item } from './item';

export class ShowCart {
    public cartId: string;
    public customerId: string;
    public items: Item[] = new Array<Item>();
    public valorTotal: number;
}