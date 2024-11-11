package com.example.ss9.repository;

import com.example.ss9.model.Category;

import java.util.List;

public interface ICategoryRepository {
    Category findById(int id);
    List<Category> findAll();
}
