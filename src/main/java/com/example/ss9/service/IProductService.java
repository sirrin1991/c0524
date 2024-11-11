package com.example.ss9.service;

import com.example.ss9.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    void save(Product product);
}
