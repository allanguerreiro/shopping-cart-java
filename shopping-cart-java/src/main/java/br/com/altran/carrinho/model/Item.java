package br.com.altran.carrinho.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "items")
public class Item {

    @Id
    private String id;
    private @NonNull String name;
    private @NonNull Double value;
    private Integer quantity;

    public Item() {
        this.quantity = 1;
    }
}
