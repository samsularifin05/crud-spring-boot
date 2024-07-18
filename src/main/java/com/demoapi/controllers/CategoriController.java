package com.demoapi.controllers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demoapi.dto.CategoriDto;
import com.demoapi.dto.ResponseData;
import com.demoapi.hepers.ResponseUtil;
import com.demoapi.model.entities.Category;
import com.demoapi.services.CategoryService;
import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoriController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseUtil responseUtil;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoriDto categoriDto, Errors errors) {
        if (errors.hasErrors()) {
            return responseUtil.handleErrors(errors);
        }

        Category category = modelMapper.map(categoriDto, Category.class);
        Category savedCategori = categoryService.save(category);

        return responseUtil.handleSuccess(Optional.of("Categori saved successfully"), Optional.of(savedCategori));
    }

    @GetMapping
    public ResponseEntity<ResponseData<Iterable<Category>>> getCategory() {
        List<Category> categories = (List<Category>) categoryService.findAll();

        return responseUtil.handleSuccess(Optional.of("Categories fetched successfully"), Optional.of(categories));

    }

    @GetMapping("/{id}")
    public Category getCategoriById(@PathVariable("id") Long id) {
        return categoryService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody CategoriDto categoriDto, Errors errors) {
        if (errors.hasErrors()) {
            return responseUtil.handleErrors(errors);
        }

        Category category = modelMapper.map(categoriDto, Category.class);
        Category savedCategori = categoryService.save(category);

        return responseUtil.handleSuccess(Optional.of("Categori saved successfully"), Optional.of(savedCategori));
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        categoryService.removeOne(id);
        return "Data Behasil Dihapus";
    }

}
