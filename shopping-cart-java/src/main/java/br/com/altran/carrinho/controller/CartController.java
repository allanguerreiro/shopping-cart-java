package br.com.altran.carrinho.controller;

import br.com.altran.carrinho.dto.CartRequest;
import br.com.altran.carrinho.model.Cart;
import br.com.altran.carrinho.services.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST}, allowCredentials = "true", origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(value = "/create")
    private ResponseEntity create(@RequestBody CartRequest cartRequest) {
        try {
            Cart cart = cartService.saveOrUpdate(cartRequest);
            return new ResponseEntity<>(cart, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Create Cart method error {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/cartbycustomerid")
    private ResponseEntity getCartByCustomerId(@RequestBody CartRequest cartRequest) {
        try {
            Cart cart = cartService.findCartByCustomerId(cartRequest);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Find Cart by Customer id method error {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/carts")
    private ResponseEntity getCarts() {
        try {
            List<Cart> carts = cartService.findAllCarts();
            return new ResponseEntity<>(carts, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Find Cart by Customer id method error {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
