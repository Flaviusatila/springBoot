package com.iftm.course.resources;

import com.iftm.course.dto.UserDTO;
import com.iftm.course.dto.UserInsertDTO;
import com.iftm.course.entities.User;
import com.iftm.course.repository.UserRepository;
import com.iftm.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
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
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> listDto = service.findAll();
        return ResponseEntity.ok().body( listDto );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> finById(@PathVariable Long id ){
        UserDTO dto = service.finByid( id );
        return ResponseEntity.ok().body( dto );
    }


    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody @Valid UserInsertDTO dto){
        UserDTO newDto = service.insert( dto );
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
    public ResponseEntity<UserDTO> update(@PathVariable Long id,@Valid @RequestBody UserDTO dto){
        dto = service.update( id,dto );
        return ResponseEntity.ok().body( dto );
    }

}
