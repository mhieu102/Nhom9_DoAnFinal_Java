package com.example.Nhom9_DoAnFinal_Java.repository;

import com.example.Nhom9_DoAnFinal_Java.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
