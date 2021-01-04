package com.iftm.course.resources;

import com.iftm.course.dto.OrderDTO;
import com.iftm.course.entities.Order;
import com.iftm.course.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService service;

//    @GetMapping
//    public ResponseEntity<Order> findAll(){
//        Order user = new Order(1L,"Maria","maria@gmail.com","999999999","12345");
//        return ResponseEntity.ok().body( user );
//    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll(){
        List<OrderDTO> list = service.findAll();
        return ResponseEntity.ok().body( list );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> finById(@PathVariable Long id ){
        OrderDTO dto = service.findById( id );
        return ResponseEntity.ok().body( dto );
    }


}
