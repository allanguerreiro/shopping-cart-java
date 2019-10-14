package br.com.altran.carrinho.controller;

import br.com.altran.carrinho.dto.ItemRequest;
import br.com.altran.carrinho.model.Item;
import br.com.altran.carrinho.services.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE}, allowCredentials = "true", origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/item")
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    private ResponseEntity items() {
        try {
            List<Item> items = itemService.findAll();
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Find Items method error {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/itembyname/{name}")
    private ResponseEntity getItemByName(@PathVariable(value = "name") String name) {
        try {
            Item item = itemService.findByName(name);
            return new ResponseEntity<>(item, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Find Item by Name method error {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/itembyid/{id}")
    private ResponseEntity getItemById(@PathVariable(value = "id") String id) {
        try {
            Item item = itemService.findItemById(id);
            return new ResponseEntity<>(item, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Find Item by Id method error {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/create")
    private ResponseEntity create(@RequestBody ItemRequest itemRequest) {
        try {
            Item item = itemService.saveOrUpdate(itemRequest);
            return new ResponseEntity<>(item, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Create Item method error {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update")
    private ResponseEntity update(@RequestBody ItemRequest itemRequest) {
        try {
            Item item = itemService.saveOrUpdate(itemRequest);
            return new ResponseEntity<>(item, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Update Item method error {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity delete(@PathVariable(value = "id") String id) {
        try {
            itemService.delete(id);
            return new ResponseEntity<>("Success to remove Item!", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Erro no delete {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
