package com.guner.repository;

import com.guner.entity.Product;
import com.guner.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
