package com.iftm.course.service;

import com.iftm.course.entities.Category;
import com.iftm.course.entities.User;
import com.iftm.course.repository.CategoryRepository;
import com.iftm.course.service.exceptions.DataBaseException;
import com.iftm.course.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.NotSupportedException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category findById(Long id){
        Optional<Category> obj = repository.findById( id );
        return obj.orElseThrow( () -> new ResourceNotFoundException( id ) );
    }

    public Category insert(Category obj) {
        try{
            return repository.save( obj );
        }catch (Exception e){
            throw new ResourceNotFoundException( e );
        }

    }

    public void delete(Long id) {
        try{
            repository.deleteById( id );
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException( id );
        }catch (DataIntegrityViolationException e ){
            throw new DataBaseException( e.getMessage() );
        }
    }

    public Category update(Long id, Category obj) {
        try {
            Category entity = repository.getOne( id );
            updateData( entity , obj );
            return repository.save( entity );
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException( id );
        }
    }
    private void updateData(Category entity, Category obj) {
        entity.setName( obj.getName() );
    }

}
