package com.guner.controller;

import com.guner.entity.Product;
import com.guner.service.ProductService;
import com.guner.service.TransactionalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TransactionalController {

    private TransactionalService transactionalService;

    @PostMapping("createProduct")
    public ResponseEntity<Product> createProduct() {
        Product savedproduct = transactionalService.createProduct();
        return new ResponseEntity<>(savedproduct, HttpStatus.CREATED);
    }

    @PostMapping("createProductAndUserTransactional")
    public ResponseEntity<Void> createProductAndUserTransactional() {
        transactionalService.createProductAndUserTransactional();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("createProductAndUserTransactional2")
    public ResponseEntity<Void> createProductAndUserTransactional2() {
        transactionalService.createProductAndUserTransactional2();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("createProductAndUserTransactional3")
    public ResponseEntity<Void> createProductAndUserTransactional3() {
        transactionalService.createProductAndUserTransactional3();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("createProductAndUserTransactional4")
    public ResponseEntity<Void> createProductAndUserTransactional4() {
        transactionalService.createProductAndUserTransactional4();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("createProductTransactional")
    public ResponseEntity<Void> createProductTransactional() {
        transactionalService.createProductTransactional();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("createProductTransactional2")
    public ResponseEntity<Void> createProductTransactional2() {
        try {
            transactionalService.createProductTransactional2();
        } catch (Exception e) {
            log.info("Exception occured but transaction was not rolled back.");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("createProductTransactional3")
    public ResponseEntity<Void> createProductTransactional3() {
        transactionalService.createProductTransactional3();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("createProductTransactional4")
    public ResponseEntity<Void> createProductTransactional4() {
        transactionalService.createProductTransactional4();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("createProductTransactional5")
    public ResponseEntity<Void> createProductTransactional5() {
        transactionalService.createProductTransactional5();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("createProductTransactional6")
    public ResponseEntity<Void> createProductTransactional6() {
        transactionalService.createProductTransactional6();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("createProductTransactional7")
    public ResponseEntity<Void> createProductTransactional7() {
        transactionalService.createProductTransactional7();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("createProductTransactional8")
    public ResponseEntity<Void> createProductTransactional8() {
        transactionalService.createProductTransactional8();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("createProductTransactional9")
    public ResponseEntity<Void> createProductTransactional9() {
        transactionalService.createProductTransactional9();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

