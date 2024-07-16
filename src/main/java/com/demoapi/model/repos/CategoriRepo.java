package com.demoapi.model.repos;

import org.springframework.data.repository.CrudRepository;

import com.demoapi.model.entities.Category;

public interface CategoriRepo extends CrudRepository<Category, Long> {

}
