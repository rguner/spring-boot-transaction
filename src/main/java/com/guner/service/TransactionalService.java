package com.guner.service;

import com.guner.entity.Product;
import com.guner.entity.User;
import com.guner.exception.CreateRuntimeException;
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

    @Transactional(noRollbackFor = Exception.class)
    public void createProductAndUserTransactional2() {
        log.debug("------ createProduct ------");
        Product product = new Product();
        product.setDescription("This is an example with exception but caught Exception.");
        product.setPrice(121);
        product.setTitle("121 Product");
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

    @Transactional
    public void createProductAndUserTransactional3() {
        log.debug("------ createProduct ------");
        Product product = new Product();
        product.setDescription("This is an example with runtime exception but caught runtimeException.");
        product.setPrice(122);
        product.setTitle("122 Product");
        Product product1 = productService.createProductWithParam(product);
        Long productId = product1.getId();
        log.info("Product created with id: " + product1.getId());

        Product createdProduct = productService.getProductById(productId);
        log.info("Product created " + createdProduct);


        try {
            log.debug("------ createUserTransactional ------");
            User user = new User();
            user.setFirstName("Name3");
            user.setLastName("LastName3");
            user.setEmail("email3");
            userService.createUserTransactionalWithUncheckedException(user);
        } catch (RuntimeException e) {
            log.error("Exception occurred", e);
        }
    }

    @Transactional
    public void createProductAndUserTransactional4() {
        log.debug("------ createProduct ------");
        Product product = new Product();
        product.setDescription("This is an example with runtime exception but caught runtimeException and txtype requiresNew");
        product.setPrice(123);
        product.setTitle("123 Product");
        productService.createProductWithParam(product);
        try {
            log.debug("------ createUserTransactional ------");
            User user = new User();
            user.setFirstName("Name4");
            user.setLastName("LastName4");
            user.setEmail("email4");
            userService.createUserTransactionalWithUncheckedExceptionTxTypeRequiresNew(user);
        } catch (RuntimeException e) {
            log.error("Exception occurred", e);
        }
    }

    @Transactional
    public void createProductTransactional() {
        log.debug("------ createProduct ------");
        Product product = new Product();
        product.setDescription("This is an example with runtime exception");
        product.setPrice(23);
        product.setTitle("23 Product");
        Product product1 = productService.createProductWithParam(product);
        Long productId = product1.getId();
        log.info("Product created with id: " + product1.getId());

        Product createdProduct = productService.getProductById(productId);
        log.info("Product created " + createdProduct);

        throw new RuntimeException("Manuel Runtime Exception during Product creation");
    }

    @Transactional
    public void createProductTransactional2() throws Exception {
        log.debug("------ createProduct ------");
        Product product = new Product();
        product.setDescription("This is an example with exception");
        product.setPrice(24);
        product.setTitle("24 Product");
        Product product1 = productService.createProductWithParam(product);
        Long productId = product1.getId();
        log.info("Product created with id: " + product1.getId());

        Product createdProduct = productService.getProductById(productId);
        log.info("Product created " + createdProduct);

        throw new Exception("Manuel Exception during Product creation");
    }

    @Transactional
    public void createProductTransactional3() {
        log.debug("------ createProduct ------");
        Product product = new Product();
        product.setDescription("This is an example with CreateRuntimeException");
        product.setPrice(25);
        product.setTitle("25 Product");
        Product product1 = productService.createProductWithParam(product);
        Long productId = product1.getId();
        log.info("Product created with id: " + product1.getId());

        Product createdProduct = productService.getProductById(productId);
        log.info("Product created " + createdProduct);

        throw new CreateRuntimeException("Manuel Runtime Exception during Product creation");
    }

    @Transactional
    public void createProductTransactional4() {
        try {
            log.debug("------ createProduct ------");
            Product product = new Product();
            product.setDescription("This is an example with CreateRuntimeException");
            product.setPrice(26);
            product.setTitle("26 Product");
            Product product1 = productService.createProductWithParam(product);
            Long productId = product1.getId();
            log.info("Product created with id: " + product1.getId());

            Product createdProduct = productService.getProductById(productId);
            log.info("Product created " + createdProduct);
            throw new Exception("Manuel Exception during Product creation in try block");
        } catch (Exception e) {
            log.error("Exception occurred", e);
            throw new CreateRuntimeException("Exception caught in catch block", e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void createProductTransactional5() {
        try {
            log.debug("------ createProduct ------");
            Product product = new Product();
            product.setDescription("This is an example with CreateRuntimeException and rollbackFor Exception.class");
            product.setPrice(27);
            product.setTitle("27 Product");
            Product product1 = productService.createProductWithParam(product);
            Long productId = product1.getId();
            log.info("Product created with id: " + product1.getId());

            Product createdProduct = productService.getProductById(productId);
            log.info("Product created " + createdProduct);
            throw new Exception("Manuel Exception during Product creation in try block");
        } catch (Exception e) {
            log.error("Exception occurred", e);
            throw new CreateRuntimeException("Exception caught in catch block", e);
        }
    }

    @Transactional(noRollbackFor = Exception.class)
    public void createProductTransactional6() {
        try {
            log.debug("------ createProduct ------");
            Product product = new Product();
            product.setDescription("This is an example with CreateRuntimeException and noRollbackFor Exception.class");
            product.setPrice(28);
            product.setTitle("28 Product");
            Product product1 = productService.createProductWithParam(product);
            Long productId = product1.getId();
            log.info("Product created with id: " + product1.getId());

            Product createdProduct = productService.getProductById(productId);
            log.info("Product created " + createdProduct);
            throw new Exception("Manuel Exception during Product creation in try block");
        } catch (Exception e) {
            log.error("Exception occurred", e);
            throw new CreateRuntimeException("Exception caught in catch block", e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void createProductTransactional7() {
        try {
            log.debug("------ createProduct ------");
            Product product = new Product();
            product.setDescription("This is an example with CreateRuntimeException and rollbackFor Exception.class");
            product.setPrice(29);
            product.setTitle("29 Product");
            Product product1 = productService.createProductWithParam(product);
            Long productId = product1.getId();
            log.info("Product created with id: " + product1.getId());

            Product createdProduct = productService.getProductById(productId);
            log.info("Product created " + createdProduct);
            throw new Exception("Manuel Exception during Product creation in try block");
        } catch (Exception e) {
            log.error("Exception occurred", e);
            Product product = new Product();
            product.setDescription("This is an example with save in catch");
            product.setPrice(30);
            product.setTitle("30 Product");
            Product product2 = productService.createProductWithParam(product);
            throw new CreateRuntimeException("Exception caught in catch block", e);
        }
    }

}
