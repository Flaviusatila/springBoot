package com.iftm.course.resources;

import com.iftm.course.dto.CategoryDTO;
import com.iftm.course.entities.Category;
import com.iftm.course.entities.User;
import com.iftm.course.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> list = service.findAll();
        return ResponseEntity.ok().body( list );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> finById(@PathVariable Long id ){
        CategoryDTO dto = service.findById( id );
        return ResponseEntity.ok().body( dto );
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto){
        dto = service.insert( dto );
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path( "/{id}")
                .buildAndExpand( dto.getId() )
                .toUri();

        return ResponseEntity.created(uri).body( dto );
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete( id );
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id,@RequestBody CategoryDTO dto){
        dto = service.update( id,dto );
        return ResponseEntity.ok().body( dto );
    }

}
