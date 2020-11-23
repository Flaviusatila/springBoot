package com.iftm.course.resources;

import com.iftm.course.entities.User;
import com.iftm.course.repository.UserRepository;
import com.iftm.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.jws.soap.SOAPBinding;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

//    @GetMapping
//    public ResponseEntity<User> findAll(){
//        User user = new User(1L,"Maria","maria@gmail.com","999999999","12345");
//        return ResponseEntity.ok().body( user );
//    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = service.findAll();
        return ResponseEntity.ok().body( list );
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> finById(@PathVariable Long id ){
        User user = service.finByid( id );
        return ResponseEntity.ok().body( user );
    }


    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj){
        obj = service.insert( obj );
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path( "/{id}")
                .buildAndExpand( obj.getId() )
                .toUri();

        return ResponseEntity.created(uri).body( obj );
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete( id );
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id,@RequestBody User obj){
        obj = service.update( id,obj );
        return ResponseEntity.ok().body( obj );
    }

}
