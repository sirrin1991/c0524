package com.example.ss9.service;

import com.example.ss9.model.Product;
import com.example.ss9.repository.IProductRepository;
import com.example.ss9.repository.ProductRepository;

import java.util.List;

public class ProductService implements IProductService{
    private final IProductRepository iProductRepository = new ProductRepository();
    @Override
    public List<Product> findAll() {
        return iProductRepository.findAll();
    }

    @Override
    public void save(Product product) {
        iProductRepository.save(product);
    }
}
