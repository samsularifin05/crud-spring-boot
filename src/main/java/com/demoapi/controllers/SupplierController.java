package com.demoapi.controllers;

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

import com.demoapi.dto.ResponseData;
import com.demoapi.dto.SupplierDto;
import com.demoapi.hepers.ResponseUtil;
import com.demoapi.model.entities.Supplier;
import com.demoapi.services.SupplierService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseUtil responseUtil;

    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> createSupplier(@Valid @RequestBody SupplierDto supplierDto,
            Errors erros) {

        if (erros.hasErrors()) {
            return responseUtil.handleErrors(erros);
        }

        if (supplierService.existsByEmail(supplierDto.getEmail())) {
            return responseUtil.handleCustomError("Email is already in use");
        }

        Supplier supplier = modelMapper.map(supplierDto, Supplier.class);

        return responseUtil.handleSuccess(supplierService.save(supplier));

    }

    @GetMapping
    public Iterable<Supplier> getSupplier() {
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable("id") Long id) {
        return supplierService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Supplier>> updateSupplier(@Valid @RequestBody SupplierDto supplierDto,
            Errors erros) {

        if (erros.hasErrors()) {
            return responseUtil.handleErrors(erros);
        }

        if (supplierService.existsByEmail(supplierDto.getEmail())) {
            return responseUtil.handleCustomError("Email is already in use");
        }

        Supplier supplier = modelMapper.map(supplierDto, Supplier.class);

        return responseUtil.handleSuccess(supplierService.save(supplier));
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        supplierService.removeOne(id);
        return "Data Behasil Dihapus";
    }

}
