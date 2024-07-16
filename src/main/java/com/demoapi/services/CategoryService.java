package com.demoapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demoapi.model.entities.Category;
import com.demoapi.model.repos.CategoriRepo;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoriRepo categoriRepo;

    public Category save(Category category) {
        return categoriRepo.save(category);
    }

    public Category findOne(Long id) {
        Optional<Category> category = categoriRepo.findById(id);
        if (!category.isPresent()) {
            return null;
        }
        return category.get();
    }

    public Iterable<Category> findAll() {
        return categoriRepo.findAll();
    }

    public void removeOne(Long id) {
        categoriRepo.deleteById(id);
    }
}
