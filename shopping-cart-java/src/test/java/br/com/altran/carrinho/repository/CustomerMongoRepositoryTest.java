package br.com.altran.carrinho.repository;

import br.com.altran.carrinho.model.Customer;
import br.com.altran.carrinho.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerMongoRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        Customer customerA = new Customer(null, "Allan Carneiro", "allancarneiro@gmail.com");
        Customer customerB = new Customer(null, "Allan Guerreiro", "allanguerreiro@gmail.com");

        //save customer, verify has ID value after save
        assertNull(customerA.getId());
        assertNull(customerB.getId());
        log.debug("Customer A id before save {}", customerA.getId());
        log.debug("Customer B id before save {}", customerB.getId());

        this.customerRepository.save(customerA);
        this.customerRepository.save(customerB);

        assertNotNull(customerA.getId());
        assertNotNull(customerB.getId());

        log.debug("Customer A id after save {}", customerA.getId());
        log.debug("Customer B id after save {}", customerB.getId());
    }

    @Test
    public void testFetchData() {
        Iterable<Customer> users = customerRepository.findAll();
        assertNotNull(users);
        for (Customer customer : users) {
            log.debug("Customer id {}", customer.getId());
            log.debug("Customer name {}", customer.getName());
            log.debug("Customer email {}", customer.getEmail());
        }
        assertEquals(((List<Customer>) users).size(), 2);
    }

    @Test
    public void testDataUpdate() {
        /*Test update*/
        Customer customerB = customerRepository.findCustomerByName("Allan Guerreiro");
        customerB.setEmail("allanguerreiro@yahoo.com");
        customerRepository.save(customerB);

        Customer customerUpdated = customerRepository.findCustomerByName("Allan Guerreiro");
        assertNotNull(customerUpdated);
        assertEquals("allanguerreiro@yahoo.com", customerUpdated.getEmail());
    }

    @After
    public void tearDown() throws Exception {
        this.customerRepository.deleteAll();
    }
}
