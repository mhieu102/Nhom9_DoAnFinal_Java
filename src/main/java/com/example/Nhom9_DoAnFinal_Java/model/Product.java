package com.example.Nhom9_DoAnFinal_Java.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "ten san pham khong dc de trong")
    private String name;
    @NotNull(message = "gia san pham khong dc de trong")
    @Min(value = 1,message = "gia san pham khong nho hon 1")
    @Max(value = 999999,message = "gia san pham khong lon hon 999999")
    private double price;
    @Size(max = 10000, message = "mo ta san pham khong lon hon 10000")
    private String description;
    private String images;
    private  Long Sl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name ="user_id", referencedColumnName = "id")
    private User user;
}