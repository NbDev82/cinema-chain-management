package com.example.cinemachainmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItem   {
    private Long productId;
    private String name;
    private double price;
    private String img_url;
    private int pty =1 ;
}
