package com.guner.service;

import com.guner.entity.Product;
import com.guner.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.get();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getId()).get();
        existingProduct.setTitle(product.getTitle());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        Product updatedProduct = productRepository.save(existingProduct);
        return updatedProduct;
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
