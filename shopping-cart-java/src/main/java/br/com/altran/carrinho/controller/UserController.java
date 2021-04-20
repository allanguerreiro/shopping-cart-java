package br.com.altran.carrinho.controller;

import br.com.altran.carrinho.dto.UserRequest;
import br.com.altran.carrinho.dto.UserResponse;
import br.com.altran.carrinho.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST}, allowCredentials = "true", origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/authenticate")
    private ResponseEntity authenticate(@RequestBody UserRequest userRequest) {
        try {
            UserResponse userResponse = userService.authenticate(userRequest);
            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Method error {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
