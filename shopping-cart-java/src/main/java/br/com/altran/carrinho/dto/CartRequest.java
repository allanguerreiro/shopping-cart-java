package br.com.altran.carrinho.dto;

import br.com.altran.carrinho.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest extends BaseRequest {

    private String customerId;
    private List<Item> items;
}
