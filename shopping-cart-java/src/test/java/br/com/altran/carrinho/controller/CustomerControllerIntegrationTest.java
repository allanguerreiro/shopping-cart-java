package br.com.altran.carrinho.controller;

import br.com.altran.carrinho.Application;
import br.com.altran.carrinho.model.Customer;
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
public class CustomerControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void testGetAllCustomers() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/v1/customer/customers", HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetCustomerByName() {
        Customer customer = restTemplate.getForObject(getRootUrl() + "/api/v1/customer/customerbyname/Allan", Customer.class);
        log.debug(customer.getId());
        assertNotNull(customer);
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setEmail("admin@gmail.com");
        customer.setName("Admin");

        ResponseEntity<Customer> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/customer/create", customer, Customer.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = new Customer();
        customer.setEmail("admin1@gmail.com");
        customer.setName("Admin");

        restTemplate.put(getRootUrl() + "/api/v1/customer/update", customer);

        Customer updatedCustomer = restTemplate.getForObject(getRootUrl() + "/api/v1/customer/customerbyname/Admin", Customer.class);
        assertNotNull(updatedCustomer);
    }

    @Test
    public void testDeleteCustomer() {
        this.testCreateCustomer();
        Customer customer = restTemplate.getForObject(getRootUrl() + "/api/v1/customer/customerbyname/Admin", Customer.class);
        assertNotNull(customer);

        restTemplate.delete(getRootUrl() + "/api/v1/customer/delete/" + customer.getId());
    }
}
