package com.demoapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demoapi.model.entities.Product;
import com.demoapi.model.entities.Supplier;
import com.demoapi.model.repos.ProductRepo;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public Product findOne(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (!optionalProduct.isPresent()) {
            return null;
        }
        return optionalProduct.get();
    }

    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    public void removeOne(Long id) {
        productRepo.deleteById(id);
    }

    public void addSupplier(Supplier supplier, Long productId) {
        Product product = findOne(productId);
        if (product == null) {
            throw new RuntimeException("Product id : " + productId + " tidak ada");
        }
        product.getSupplier().add(supplier);
        save(product);
    }

}
