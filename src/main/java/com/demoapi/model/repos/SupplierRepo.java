package com.demoapi.model.repos;

import org.springframework.data.repository.CrudRepository;

import com.demoapi.model.entities.Supplier;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {
    boolean existsByEmail(String email);
}
