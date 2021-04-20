package br.com.altran.carrinho.services;

import br.com.altran.carrinho.dto.UserRequest;
import br.com.altran.carrinho.dto.UserResponse;

public interface UserService {
    UserResponse authenticate(UserRequest userRequest);
}
