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
    public synchronized List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public synchronized Item saveOrUpdate(ItemRequest itemRequest) {
        return itemRepository.save(itemRequestToItem.convert(itemRequest));
    }

    @Override
    public synchronized void delete(String id) {
        itemRepository.deleteById(id);
    }

    @Override
    public synchronized Item findByName(String name) {
        return itemRepository.findByName(name);
    }

    @Override
    public synchronized Item findItemById(String id) {
        return itemRepository.findItemById(id);
    }
}
