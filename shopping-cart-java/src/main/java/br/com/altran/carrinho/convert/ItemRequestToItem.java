package br.com.altran.carrinho.convert;

import br.com.altran.carrinho.dto.ItemRequest;
import br.com.altran.carrinho.model.Item;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ItemRequestToItem implements Converter<ItemRequest, Item> {

    @Override
    public Item convert(ItemRequest itemRequest) {
        Item item = new Item();
        if (!StringUtils.isEmpty(itemRequest.getId())) {
            item.setId(itemRequest.getId());
        }
        item.setName(itemRequest.getName());
        item.setValue(itemRequest.getValue());
        item.setQuantity(itemRequest.getQuantity());
        return item;
    }
}
