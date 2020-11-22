package com.iftm.course.resources;

import com.iftm.course.entities.User;
import com.iftm.course.repository.UserRepository;
import com.iftm.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
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


}
