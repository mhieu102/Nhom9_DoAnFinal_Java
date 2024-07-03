package com.example.Nhom9_DoAnFinal_Java.Controller;

import com.example.Nhom9_DoAnFinal_Java.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    
    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        return "/cart/cart";
    }
    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity) {
        cartService.addToCart(productId, quantity);
        return "redirect:/cart";
    }
    
    @PostMapping("/update")
    @ResponseBody
    public Map<String, Double> updateQuantity(@RequestParam Long productId, @RequestParam int quantity) {
        cartService.updateQuantity(productId, quantity);
        double newPrice = cartService.getCartItems().stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst()
            .map(item -> item.getProduct().getPrice() * item.getQuantity())
            .orElse(0.0);
        Map<String, Double> response = new HashMap<>();
        response.put("newPrice", newPrice);
        response.put("totalPrice", cartService.getTotalPrice());
        return response;
    }
    
    @GetMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId) {
        cartService.removeFromCart(productId);
        return "redirect:/cart";
    }
    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }
    
    @GetMapping("/checkout")
    public String checkout() {
        return "/home/checkout";
    }
}
