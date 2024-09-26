package com.example.learning_dev_2024.repository;

import com.example.learning_dev_2024.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
