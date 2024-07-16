package com.demoapi.model.repos;

import org.springframework.data.repository.CrudRepository;

import com.demoapi.model.entities.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {
}
