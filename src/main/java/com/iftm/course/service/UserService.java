package com.iftm.course.service;

import com.iftm.course.entities.User;
import com.iftm.course.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User finByid(Long id){
        Optional<User> userId = repository.findById( id );
        return userId.get();
    }

}
