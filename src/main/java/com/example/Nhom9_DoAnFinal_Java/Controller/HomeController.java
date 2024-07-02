package com.example.Nhom9_DoAnFinal_Java.Controller;

import com.example.Nhom9_DoAnFinal_Java.model.Product;
import com.example.Nhom9_DoAnFinal_Java.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;
    @GetMapping("/")
    public String hello(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "home/home";
    }
    @GetMapping("/product-detail/{id}")
    public String productDetail(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "home/product-detail";
    }
    @GetMapping("/shop")
    public String shop(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "home/shop";
    }
}
