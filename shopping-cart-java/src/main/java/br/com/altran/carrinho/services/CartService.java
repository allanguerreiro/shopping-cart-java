package br.com.altran.carrinho.services;

import br.com.altran.carrinho.dto.CartRequest;
import br.com.altran.carrinho.model.Cart;

public interface CartService {
    Cart saveOrUpdate(CartRequest cartRequest);

    Cart findCartByCustomerId(CartRequest cartRequest);
}
