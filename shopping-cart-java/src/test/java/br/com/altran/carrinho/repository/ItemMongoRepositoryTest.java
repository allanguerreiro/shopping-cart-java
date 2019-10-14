package br.com.altran.carrinho.repository;

import br.com.altran.carrinho.model.Item;
import br.com.altran.carrinho.repository.ItemRepository;
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
public class ItemMongoRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Before
    public void setUp() throws Exception {
        Item item1 = new Item(null, "Impressora", 650.00D, 1);
        Item item2 = new Item(null, "Computador", 3500.00D, 1);

        //save item, verify has ID value after save
        assertNull(item1.getId());
        assertNull(item2.getId());
        log.debug("Item 1 id before save {}", item1.getId());
        log.debug("Item 2 id before save {}", item1.getId());

        this.itemRepository.save(item1);
        this.itemRepository.save(item2);

        assertNotNull(item1.getId());
        assertNotNull(item2.getId());
    }

    @Test
    public void testFetchData() {
        Iterable<Item> items = itemRepository.findAll();
        assertNotNull(items);
    }

    @Test
    public void testDataUpdate() {
        /*Test update*/
        Item item = itemRepository.findByName("Impressora");
        item.setValue(2000.00D);
        item.setQuantity(1);
        itemRepository.save(item);

        Item itemUpdated = itemRepository.findByName("Impressora");
        assertNotNull(itemUpdated);
        assertNotEquals(650.00D, itemUpdated.getValue());
    }

    @After
    public void tearDown() throws Exception {
        this.itemRepository.deleteAll();
    }
}
