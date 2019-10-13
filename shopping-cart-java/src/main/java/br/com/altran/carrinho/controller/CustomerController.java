package br.com.altran.carrinho.controller;

import br.com.altran.carrinho.dto.CustomerRequest;
import br.com.altran.carrinho.model.Customer;
import br.com.altran.carrinho.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE}, allowCredentials = "true", origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    private ResponseEntity customers() {
        try {
            List<Customer> customers = customerService.findAll();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Find All Customers method error {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customerbyname/{name}")
    private ResponseEntity getCustomerByName(@PathVariable(value = "name") String name) {
        try {
            Customer customer = customerService.findCustomerByName(name);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Find Customer by Name method error {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customerbyemail/{email}")
    private ResponseEntity getCustomerByEmail(@PathVariable(value = "email") String email) {
        try {
            Customer customer = customerService.findCustomerByEmail(email);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Find Customer by Name method error {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/create")
    private ResponseEntity create(@RequestBody CustomerRequest customerRequest) {
        try {
            Customer customer = customerService.saveOrUpdate(customerRequest);
            return new ResponseEntity<>(customer, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Create Customer method error {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update")
    private ResponseEntity update(@RequestBody CustomerRequest customerRequest) {
        try {
            Customer customer = customerService.saveOrUpdate(customerRequest);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Update Customer method error {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity delete(@PathVariable(value = "id") String id) {
        try {
            customerService.delete(id);
            return new ResponseEntity<>("Success to remove Customer!", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Delete Customer method error {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
