package com.trotot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trotot.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
}