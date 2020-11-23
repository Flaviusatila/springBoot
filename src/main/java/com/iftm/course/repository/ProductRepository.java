package com.iftm.course.repository;

import com.iftm.course.entities.Category;
import com.iftm.course.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
