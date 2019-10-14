package br.com.altran.carrinho.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest extends BaseRequest {

    private String name;
    private Double value;
    private Integer quantity;
}
