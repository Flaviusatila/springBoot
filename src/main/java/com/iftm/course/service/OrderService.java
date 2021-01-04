package com.iftm.course.service;

import com.iftm.course.dto.OrderDTO;
import com.iftm.course.entities.Order;
import com.iftm.course.repository.OrderRepository;
import com.iftm.course.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements Serializable {

    private static final long serialVersionUID = -3973506331899336914L;
    @Autowired
    private OrderRepository repository;

    public List<OrderDTO> findAll(){
        List<Order> list = repository.findAll();
        return list.stream().map( e -> new OrderDTO(e) ).collect( Collectors.toList() );
    }

    public OrderDTO findById(Long id){
        Order obj = repository.findById( id )
                    .orElseThrow( () -> new ResourceNotFoundException( id ) );
        return new OrderDTO( obj );
    }

}
