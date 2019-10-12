package br.com.altran.carrinho.services;

import br.com.altran.carrinho.convert.CustomerRequestToCustomer;
import br.com.altran.carrinho.dto.CustomerRequest;
import br.com.altran.carrinho.exception.BusinessException;
import br.com.altran.carrinho.model.Customer;
import br.com.altran.carrinho.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer saveOrUpdate(CustomerRequest customerRequest) {
        return customerRepository.save(customerRequestToCustomer.convert(customerRequest));
    }

    @Override
    public Customer findByName(CustomerRequest customerRequest) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findByName(customerRequest.getName()));
        if (customer.isPresent()) {
            return customerRepository.findByName(customerRequest.getName());
        }
        throw new BusinessException("Name not found!");
    }

    @Override
    public void delete(CustomerRequest customerRequest) {
        customerRepository.delete(customerRequestToCustomer.convert(customerRequest));
    }
}
