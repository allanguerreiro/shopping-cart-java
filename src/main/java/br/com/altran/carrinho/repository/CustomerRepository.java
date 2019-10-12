package br.com.altran.carrinho.repository;

import br.com.altran.carrinho.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    List<Customer> findAll();

    Customer findByName(String name);
}
