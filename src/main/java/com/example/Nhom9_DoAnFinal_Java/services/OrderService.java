package com.example.Nhom9_DoAnFinal_Java.services;

import com.example.Nhom9_DoAnFinal_Java.model.CartItem;
import com.example.Nhom9_DoAnFinal_Java.model.Order;
import com.example.Nhom9_DoAnFinal_Java.model.OrderDetail;
import com.example.Nhom9_DoAnFinal_Java.repository.OrderDetailRepository;
import com.example.Nhom9_DoAnFinal_Java.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CartService cartService; // Assuming you have a CartService
    @Transactional
    public Order createOrder(Order order, List<CartItem> cartItems) {
        order.setCustomerName(order.getCustomerName());
        order.setAddress(order.getAddress());
        order.setNumber(order.getNumber());
        order.setDescription(order.getDescription());
        order = orderRepository.save(order);
        for (CartItem item : cartItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            orderDetailRepository.save(detail);
        }
        cartService.clearCart();
        return order;
    }
}
