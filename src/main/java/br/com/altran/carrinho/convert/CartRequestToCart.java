package br.com.altran.carrinho.convert;

import br.com.altran.carrinho.dto.CartRequest;
import br.com.altran.carrinho.model.Cart;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CartRequestToCart implements Converter<CartRequest, Cart> {

    @Override
    public Cart convert(CartRequest cartRequest) {
        Cart cart = new Cart();
        if (!StringUtils.isEmpty(cartRequest.getId())) {
            cart.setId(cartRequest.getId());
        }
        cart.setCustomerId(cartRequest.getCustomerId());
        cart.setItems(cartRequest.getItems());
        return cart;
    }
}
