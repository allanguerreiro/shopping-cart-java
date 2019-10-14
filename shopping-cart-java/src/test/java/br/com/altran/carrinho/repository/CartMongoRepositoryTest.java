package br.com.altran.carrinho.repository;

import br.com.altran.carrinho.model.Cart;
import br.com.altran.carrinho.model.Customer;
import br.com.altran.carrinho.model.Item;
import br.com.altran.carrinho.repository.CartRepository;
import br.com.altran.carrinho.repository.CustomerRepository;
import br.com.altran.carrinho.repository.ItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMongoRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Before
    public void setUp() throws Exception {
        Customer customer = new Customer(null, "Allan Carneiro", "allancarneiro@gmail.com");
        customerRepository.save(customer);
        customer = customerRepository.findCustomerByName("Allan Carneiro");

        Item itemA = new Item(null, "Celular Motorola", 1000.0D, 1);
        itemRepository.save(itemA);
        itemA = itemRepository.findByName("Celular Motorola");

        Item itemB = new Item(null, "Monitor LG", 1500.0D, 1);
        itemRepository.save(itemB);
        itemB = itemRepository.findByName("Monitor LG");

        List<Item> items = new ArrayList<>();
        items.add(itemA);
        items.add(itemB);

        Cart cart = new Cart(null, customer.getId(), items);
        cartRepository.save(cart);

        ObjectMapper objectMapper = new ObjectMapper();
        for (Cart cart1 : cartRepository.findAll()) {
            log.debug("Cart after save {}", objectMapper.writeValueAsString(cart1));
        }
    }

    @Test
    public void testFetchData() {
        Iterable<Cart> carts = cartRepository.findAll();
        assertNotNull(carts);
        for (Cart cart : carts) {
            log.debug("Cart id {}", cart.getId());
            log.debug("Cart id customer {}", cart.getCustomerId());
        }
        assertEquals(((List<Cart>) carts).size(), 1);
    }

    @Test
    public void testDataUpdate() {
        /*Test update*/
        Customer customer = customerRepository.findCustomerByName("Allan Carneiro");
        Cart cart = cartRepository.findCartByCustomerId(customer.getId());

        for (Item item : cart.getItems()) {
            item.setValue(200D);
        }

        cartRepository.save(cart);

        for (Item item : cart.getItems()) {
            assertNotSame(1000.0D, item.getValue());
        }
    }

    @After
    public void tearDown() throws Exception {
        this.customerRepository.deleteAll();
        this.itemRepository.deleteAll();
        this.cartRepository.deleteAll();
    }
}
