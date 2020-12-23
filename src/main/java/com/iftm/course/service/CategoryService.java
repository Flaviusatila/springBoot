package com.iftm.course.service;

import com.iftm.course.dto.CategoryDTO;
import com.iftm.course.dto.ProductDTO;
import com.iftm.course.entities.Category;
import com.iftm.course.entities.User;
import com.iftm.course.repository.CategoryRepository;
import com.iftm.course.service.exceptions.DataBaseException;
import com.iftm.course.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.NotSupportedException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<CategoryDTO> findAll(){
        List<Category> list = repository.findAll();
        return list.stream().map( e -> new CategoryDTO(e) ).collect( Collectors.toList());
    }

    public CategoryDTO findById(Long id){
        Optional<Category> obj = repository.findById( id );
        obj.orElseThrow( () -> new ResourceNotFoundException( id ) );
        CategoryDTO dto = new CategoryDTO(obj.get());
        return dto;
    }

    public CategoryDTO insert(CategoryDTO dto) {
        try{
            Category obj = dto.toEntity();
            repository.save( obj );
            return dto;
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

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        try {
            Category entity = repository.getOne( id );
            updateData( entity , dto );
            repository.save( entity );
            return dto;
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException( id );
        }
    }
    private void updateData(Category entity, CategoryDTO dto) {
        entity.setName( dto.getName() );
    }

}
