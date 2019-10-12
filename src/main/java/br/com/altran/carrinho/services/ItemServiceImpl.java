package br.com.altran.carrinho.services;

import br.com.altran.carrinho.convert.ItemRequestToItem;
import br.com.altran.carrinho.dto.ItemRequest;
import br.com.altran.carrinho.model.Item;
import br.com.altran.carrinho.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;
    private ItemRequestToItem itemRequestToItem;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ItemRequestToItem itemRequestToItem) {
        this.itemRepository = itemRepository;
        this.itemRequestToItem = itemRequestToItem;
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item saveOrUpdate(ItemRequest itemRequest) {
        return itemRepository.save(itemRequestToItem.convert(itemRequest));
    }

    @Override
    public void delete(ItemRequest itemRequest) {
        itemRepository.delete(itemRequestToItem.convert(itemRequest));
    }

    @Override
    public Item findByName(ItemRequest itemRequest) {
        return itemRepository.findByName(itemRequest.getName());
    }
}
