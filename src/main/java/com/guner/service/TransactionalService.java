package com.guner.service;

import com.guner.entity.Product;
import com.guner.entity.User;
import com.guner.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionalService {

    private final ProductService productService;
    private final UserService userService;

    public Product createProduct() {
        Product product = new Product();
        product.setDescription("This is an example with runtime exception but no rollback.");
        product.setPrice(10);
        product.setTitle("First Product");
        productService.createProduct(product);
        throw new RuntimeException("Runtime Exception Occured");
    }

    @Transactional
    public void createProductAndUserTransactional() {
        log.debug("------ createProduct ------");
        productService.createProduct();
        try {
            log.debug("------ createUserTransactional ------");
            userService.createUserTransactional();
        } catch (RuntimeException e) {
            System.out.println("Handle " + e.getMessage());
        }
    }

}
