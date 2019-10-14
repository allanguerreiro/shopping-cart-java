package br.com.altran.carrinho;

import br.com.altran.carrinho.model.Customer;
import br.com.altran.carrinho.model.Item;
import br.com.altran.carrinho.repository.CustomerRepository;
import br.com.altran.carrinho.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.altran.carrinho*"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Resource
    private CustomerRepository customerRepository;

    @Resource
    private ItemRepository itemRepository;

    @Bean
    public CommandLineRunner populateReviews() {
        return new ReviewPopulatorRunner();
    }

    public class ReviewPopulatorRunner implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            customerRepository.save(new Customer(null, "Allan", "allan@gmail.com"));
            itemRepository.save(new Item(null, "Celular", 1000.0, 1));
            itemRepository.save(new Item(null, "Bicicleta", 500.0, 1));
            itemRepository.save(new Item(null, "Caderno", 20.0, 1));
            itemRepository.save(new Item(null, "Caneta", 2.0, 1));
            itemRepository.save(new Item(null, "Borracha", 1.0, 1));
            itemRepository.save(new Item(null, "Apontador", 1.0, 1));
        }
    }
}
