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
        product.setTitle("10 Product");
        productService.createProduct(product);
        throw new RuntimeException("Runtime Exception Occured");
    }

    @Transactional
    public void createProductAndUserTransactional() {
        log.debug("------ createProduct ------");
        Product product = new Product();
        product.setDescription("This is an example with runtime exception but caught runtimeException.");
        product.setPrice(20);
        product.setTitle("20 Product");
        productService.createProductWithParam(product);
        try {
            log.debug("------ createUserTransactional ------");
            User user = new User();
            user.setFirstName("Name1");
            user.setLastName("LastName1");
            user.setEmail("email1");
            userService.createUserTransactionalWithUncheckedException(user);
        } catch (RuntimeException e) {
            log.error("Exception occurred", e);
        }
    }

    @Transactional
    public void createProductAndUserTransactional2() {
        log.debug("------ createProduct ------");
        Product product = new Product();
        product.setDescription("This is an example with exception but caught Exception.");
        product.setPrice(20);
        product.setTitle("20 Product");
        productService.createProduct(product);
        try {
            log.debug("------ createUserTransactional ------");
            User user = new User();
            user.setFirstName("Name2");
            user.setLastName("LastName2");
            user.setEmail("email2");
            userService.createUserTransactionalWithCheckedException(user);
        } catch (Exception e) {
            log.error("Exception occurred", e);
        }
    }

}
