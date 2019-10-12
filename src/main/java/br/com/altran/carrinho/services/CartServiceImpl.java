package br.com.altran.carrinho.services;

import br.com.altran.carrinho.convert.CartRequestToCart;
import br.com.altran.carrinho.dto.CartRequest;
import br.com.altran.carrinho.model.Cart;
import br.com.altran.carrinho.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private CartRequestToCart cartRequestToCart;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartRequestToCart cartRequestToCart) {
        this.cartRepository = cartRepository;
        this.cartRequestToCart = cartRequestToCart;
    }

    @Override
    public Cart saveOrUpdate(CartRequest cartRequest) {
        return cartRepository.save(cartRequestToCart.convert(cartRequest));
    }

    @Override
    public Cart findCartByCustomerId(CartRequest cartRequest) {
        return cartRepository.findCartByCustomerId(cartRequest.getCustomerId());
    }
}
