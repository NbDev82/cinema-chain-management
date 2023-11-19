package com.example.cinemachainmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ShoppingCartItemDTO {
    private Long shoppingCartItemId;
    private int shoppingCartItemQuantity;

    public ShoppingCartItemDTO(int shoppingCartItemQuantity) {
        this.shoppingCartItemQuantity = shoppingCartItemQuantity;
    }
}
