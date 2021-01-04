package com.iftm.course.resources;

import com.iftm.course.dto.ProductCategoryDTO;
import com.iftm.course.dto.ProductDTO;
import com.iftm.course.dto.UserDTO;
import com.iftm.course.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;


    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(){
        List<ProductDTO> list = service.findAll();
        return ResponseEntity.ok().body( list );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> finById(@PathVariable Long id ){
        ProductDTO obj = service.findById( id );
        return ResponseEntity.ok().body( obj );
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductCategoryDTO dto){
        ProductDTO newDto = service.insert( dto );
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path( "/{id}")
                .buildAndExpand( newDto.getId() )
                .toUri();
        return ResponseEntity.created(uri).body( newDto );
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductCategoryDTO dto){
        ProductDTO newDto = service.update( id,dto );
        return ResponseEntity.ok().body( newDto );
    }
}
