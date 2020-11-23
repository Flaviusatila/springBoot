package com.iftm.course.resources;

import com.iftm.course.entities.Product;
import com.iftm.course.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;

//    @GetMapping
//    public ResponseEntity<Product> findAll(){
//        Product user = new Product(1L,"Maria","maria@gmail.com","999999999","12345");
//        return ResponseEntity.ok().body( user );
//    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body( list );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> finById(@PathVariable Long id ){
        Product obj = service.findById( id );
        return ResponseEntity.ok().body( obj );
    }


}
