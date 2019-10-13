package br.com.altran.carrinho.services;

import br.com.altran.carrinho.dto.CustomerRequest;
import br.com.altran.carrinho.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Customer saveOrUpdate(CustomerRequest customerRequest);

    Customer findCustomerByName(String name);

    Customer findCustomerByEmail(String email);

    void delete(String id);
}
