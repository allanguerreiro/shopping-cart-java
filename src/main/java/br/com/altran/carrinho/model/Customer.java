package br.com.altran.carrinho.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class Customer {

    @Id
    private String id;

    private @NonNull String name;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private @NonNull String email;
}
