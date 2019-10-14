package br.com.altran.carrinho.controller;

import br.com.altran.carrinho.Application;
import br.com.altran.carrinho.model.Item;
import lombok.extern.slf4j.Slf4j;
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

import static org.junit.Assert.assertNotNull;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void testGetAllItems() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/v1/item/items", HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetItemByName() {
        Item item = restTemplate.getForObject(getRootUrl() + "/api/v1/item/itembyname/Celular", Item.class);
        log.debug(item.getId());
        assertNotNull(item);
    }

    @Test
    public void testCreateItem() {
        Item item = new Item();
        item.setName("Mouse");
        item.setValue(100.00D);
        item.setQuantity(1);

        ResponseEntity<Item> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/item/create", item, Item.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateItem() {
        Item item = restTemplate.getForObject(getRootUrl() + "/api/v1/item/itembyname/Celular", Item.class);
        item.setValue(800.00D);
        item.setName("Celular Updated");
        item.setQuantity(1);

        restTemplate.put(getRootUrl() + "/api/v1/item/update", item);

        Item updatedCustomer = restTemplate.getForObject(getRootUrl() + "/api/v1/item/itembyname/Celular Updated", Item.class);
        assertNotNull(updatedCustomer);
    }

    @Test
    public void testDeleteItem() {
        this.testCreateItem();
        Item item = restTemplate.getForObject(getRootUrl() + "/api/v1/item/itembyname/Mouse", Item.class);
        assertNotNull(item);

        restTemplate.delete(getRootUrl() + "/api/v1/item/delete/" + item.getId());
    }
}
