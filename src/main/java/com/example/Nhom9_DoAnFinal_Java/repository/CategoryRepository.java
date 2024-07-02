package com.example.Nhom9_DoAnFinal_Java.repository;

import com.example.Nhom9_DoAnFinal_Java.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
