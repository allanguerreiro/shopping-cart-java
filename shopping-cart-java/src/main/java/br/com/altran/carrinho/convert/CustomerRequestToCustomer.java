package br.com.altran.carrinho.convert;

import br.com.altran.carrinho.dto.CustomerRequest;
import br.com.altran.carrinho.model.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CustomerRequestToCustomer implements Converter<CustomerRequest, Customer> {

    @Override
    public Customer convert(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        if (!StringUtils.isEmpty(customerRequest.getId())) {
            customer.setId(customerRequest.getId());
        }
        customer.setEmail(customerRequest.getEmail());
        customer.setName(customerRequest.getName());
        return customer;
    }
}
