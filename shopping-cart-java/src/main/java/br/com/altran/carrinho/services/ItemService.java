package br.com.altran.carrinho.services;

import br.com.altran.carrinho.dto.ItemRequest;
import br.com.altran.carrinho.model.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAll();

    Item findByName(String name);

    Item findItemById(String id);

    Item saveOrUpdate(ItemRequest itemRequest);

    void delete(String id);
}
