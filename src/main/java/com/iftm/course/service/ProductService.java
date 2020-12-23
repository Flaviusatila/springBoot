package com.iftm.course.service;

import com.iftm.course.dto.ProductDTO;
import com.iftm.course.entities.Product;
import com.iftm.course.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<ProductDTO> findAll(){
        List<Product> list = repository.findAll();
        return list.stream().map( e -> new ProductDTO(e) ).collect( Collectors.toList());
    }

    public ProductDTO findById(Long id){
        Optional<Product> entity = repository.findById( id );
        ProductDTO dto = new ProductDTO(entity.get());
        return dto;
    }

}
