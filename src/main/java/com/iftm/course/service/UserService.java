package com.iftm.course.service;

import com.iftm.course.dto.UserDTO;
import com.iftm.course.dto.UserInsertDTO;
import com.iftm.course.entities.User;
import com.iftm.course.repository.UserRepository;
import com.iftm.course.service.exceptions.DataBaseException;
import com.iftm.course.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements Serializable {

    private static final long serialVersionUID = 8082511670115556352L;
    @Autowired
    private UserRepository repository;

    public List<UserDTO> findAll(){
        List<User> list = repository.findAll();
        return list.stream().map( e -> new UserDTO(e) ).collect( Collectors.toList());
    }

    public UserDTO finByid(Long id){
        Optional<User> obj = repository.findById( id );
        User entity = obj.orElseThrow( () -> new ResourceNotFoundException(  id  ) );
        return new UserDTO(entity);
    }

    public UserDTO insert(UserInsertDTO dto){
        User entity = dto.toEntity();
        entity = repository.save( entity );
        return new UserDTO(entity) ;
    }

    public void delete(Long id){
        try{
            repository.deleteById( id );
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException( id );
        }catch (DataIntegrityViolationException e ){
            throw new DataBaseException( e.getMessage() );
        }

    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto){
        try {
            User entity = repository.getOne( id );
            updateData( entity , dto );
            entity = repository.save( entity );
            return new UserDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException( id );
        }

    }

    private void updateData(User entity, UserDTO dto) {
        entity.setName( dto.getName() );
        entity.setEmail( dto.getEmail() );
        entity.setPhone( dto.getPhone() );
    }

}
