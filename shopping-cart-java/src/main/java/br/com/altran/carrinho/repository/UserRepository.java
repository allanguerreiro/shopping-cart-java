package br.com.altran.carrinho.repository;

import br.com.altran.carrinho.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(String username);

    User findUserByUsernameAndPassword(String username, String password);
}
