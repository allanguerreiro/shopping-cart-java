package br.com.altran.carrinho.services;

import br.com.altran.carrinho.dto.CartRequest;
import br.com.altran.carrinho.model.Cart;

import java.util.List;

public interface CartService {
    Cart saveOrUpdate(CartRequest cartRequest);

    Cart findCartByCustomerId(CartRequest cartRequest);

    List<Cart> findAllCarts();
}
