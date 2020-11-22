package com.iftm.course.resources;

import com.iftm.course.entities.User;
import com.iftm.course.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<User> findAll(){
        User user = new User(1L,"Maria","maria@gmail.com","999999999","12345");
        return ResponseEntity.ok().body( user );
    }

}
