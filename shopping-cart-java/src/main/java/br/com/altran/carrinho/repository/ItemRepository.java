package br.com.altran.carrinho.repository;

import br.com.altran.carrinho.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
    List<Item> findAll();

    Item findByName(String name);

    Item findItemById(String id);
}
