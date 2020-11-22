package com.iftm.course.resources;

import com.iftm.course.entities.Category;
import com.iftm.course.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categorys")
public class CategoryResource {

    @Autowired
    private CategoryService service;

//    @GetMapping
//    public ResponseEntity<Category> findAll(){
//        Category user = new Category(1L,"Maria","maria@gmail.com","999999999","12345");
//        return ResponseEntity.ok().body( user );
//    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        List<Category> list = service.findAll();
        return ResponseEntity.ok().body( list );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> finById(@PathVariable Long id ){
        Category obj = service.findById( id );
        return ResponseEntity.ok().body( obj );
    }


}
