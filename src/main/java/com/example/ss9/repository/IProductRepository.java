package com.example.ss9.repository;

import com.example.ss9.model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();
    void save(Product product);
}
