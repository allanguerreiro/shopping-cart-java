package br.com.altran.carrinho.repository;

import br.com.altran.carrinho.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
    Cart findCartByCustomerId(String customerId);
}
