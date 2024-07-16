package com.demoapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.demoapi.dto.ResponseData;
import com.demoapi.hepers.ResponseUtil;
import com.demoapi.model.entities.Product;
import com.demoapi.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ResponseUtil responseUtil;

    @PostMapping()
    public ResponseEntity<ResponseData<Product>> createProduct(@Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            return responseUtil.handleErrors(errors);
        }

        return responseUtil.handleSuccess(productService.save(product));
    }

    @GetMapping()
    public Iterable<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable("id") Long id) {
        return productService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Product>> updateProduct(@Valid @RequestBody Product product, Errors errors) {
        ResponseData<Product> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            return responseUtil.handleErrors(errors);
        }

        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.removeOne(id);
        ;
        return "Data Behasil Dihapus";
    }

}
