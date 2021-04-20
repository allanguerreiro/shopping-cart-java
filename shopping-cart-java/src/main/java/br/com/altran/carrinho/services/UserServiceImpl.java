package br.com.altran.carrinho.services;

import br.com.altran.carrinho.dto.UserRequest;
import br.com.altran.carrinho.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserResponse authenticate(UserRequest userRequest) {
        UserResponse userResponse = new UserResponse();
        userResponse.setDate(LocalDateTime.now());
        userResponse.setToken(UUID.randomUUID().toString());
        return userResponse;
    }
}
