package com.guner.service;

import com.guner.entity.Product;
import com.guner.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionalService {

    private final ProductRepository productRepository;

    public Product createProduct() {
        Product prod = new Product();
        prod.setDescription("This is an example with runtime exception but no rollback.");
        prod.setPrice(10);
        prod.setTitle("First Product");
        productRepository.save(prod);
        throw new RuntimeException("Runtime Exception Occured");
    }
}
