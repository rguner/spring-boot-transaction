package com.guner.controller;

import com.guner.entity.Product;
import com.guner.service.ProductService;
import com.guner.service.TransactionalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/transactional")
public class TransactionalController {

    private TransactionalService transactionalService;

    @PostMapping("createProduct")
    public ResponseEntity<Product> createProduct(){
        Product savedproduct = transactionalService.createProduct();
        return new ResponseEntity<>(savedproduct, HttpStatus.CREATED);
    }

    @PostMapping("createProductAndUserTransactional")
    public ResponseEntity<Void> createProductAndUserTransactional(){
        transactionalService.createProductAndUserTransactional();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("createProductAndUserTransactional2")
    public ResponseEntity<Void> createProductAndUserTransactional2(){
        transactionalService.createProductAndUserTransactional2();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("createProductAndUserTransactional3")
    public ResponseEntity<Void> createProductAndUserTransactional3(){
        transactionalService.createProductAndUserTransactional3();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

