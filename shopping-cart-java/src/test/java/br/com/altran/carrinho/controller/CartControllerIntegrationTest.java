package br.com.altran.carrinho.controller;

import br.com.altran.carrinho.Application;
import br.com.altran.carrinho.model.Cart;
import br.com.altran.carrinho.model.Customer;
import br.com.altran.carrinho.model.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void testCreateCustomer() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        Customer customer = new Customer(null, "Guerreiro", "allancarneiro@gmail.com");
        ResponseEntity<Customer> customerResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/customer/create", customer, Customer.class);
        assertNotNull(customerResponse);
        assertNotNull(customerResponse.getBody());

        customer = restTemplate.getForObject(getRootUrl() + "/api/v1/customer/customerbyname/Guerreiro", Customer.class);
        assertNotNull(customer);

        Item itemA = restTemplate.getForObject(getRootUrl() + "/api/v1/item/itembyname/Celular", Item.class);
        assertNotNull(itemA);

        Item itemB = restTemplate.getForObject(getRootUrl() + "/api/v1/item/itembyname/Apontador", Item.class);
        assertNotNull(itemB);

        List<Item> items = new ArrayList<>();
        items.add(itemA);
        items.add(itemB);

        Cart cart = new Cart(null, customer.getId(), items);
        ResponseEntity<Cart> cartResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/cart/create", cart, Cart.class);
        assertNotNull(cartResponse);
        assertNotNull(cartResponse.getBody());
    }

    @After
    public void testGetAllCarts() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/v1/cart/carts", HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }
}
