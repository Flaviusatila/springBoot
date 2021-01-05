package com.iftm.course.resources;

import com.iftm.course.dto.ProductCategoryDTO;
import com.iftm.course.dto.ProductDTO;
import com.iftm.course.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;


    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAllPaged(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String  orderBy){
        PageRequest pageRequest  = PageRequest.of( page,linesPerPage, Sort.Direction.valueOf( direction ), orderBy);
        Page<ProductDTO> list = service.findAllPaged(pageRequest);
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

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete( id );
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductCategoryDTO dto){
        ProductDTO newDto = service.update( id,dto );
        return ResponseEntity.ok().body( newDto );
    }


}
