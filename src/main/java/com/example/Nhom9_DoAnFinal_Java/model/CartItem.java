package com.example.Nhom9_DoAnFinal_Java.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor

public class CartItem {
    private Product product;
    private int quantity;
    
}
