package br.com.altran.carrinho.services;

import br.com.altran.carrinho.convert.CustomerRequestToCustomer;
import br.com.altran.carrinho.dto.CustomerRequest;
import br.com.altran.carrinho.model.Customer;
import br.com.altran.carrinho.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerRequestToCustomer customerRequestToCustomer;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerRequestToCustomer customerRequestToCustomer) {
        this.customerRepository = customerRepository;
        this.customerRequestToCustomer = customerRequestToCustomer;
    }

    @Override
    public synchronized List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public synchronized Customer saveOrUpdate(CustomerRequest customerRequest) {
        return customerRepository.save(customerRequestToCustomer.convert(customerRequest));
    }

    @Override
    public synchronized Customer findCustomerByName(String name) {
        return customerRepository.findCustomerByName(name);
    }

    @Override
    public synchronized void delete(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public synchronized Customer findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }
}
