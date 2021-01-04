package com.iftm.course.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iftm.course.entities.Order;
import com.iftm.course.entities.User;
import com.iftm.course.entities.enums.OrderStatus;

import java.io.Serializable;
import java.time.Instant;

public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 1627948677893961422L;

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd 'T' HH:mm:ss 'Z'", timezone = "GMT")
    private Instant moment;
    private OrderStatus orderStatus;
    private Long clientId;
    private String clienteName;
    private String clenteEmail;

    public OrderDTO() {
    }

    public OrderDTO(Long id, Instant moment, OrderStatus orderStatus, Long clientId, String clienteName, String clenteEmail) {
        this.id = id;
        this.moment = moment;
        this.orderStatus = orderStatus;
        this.clientId = clientId;
        this.clienteName = clienteName;
        this.clenteEmail = clenteEmail;
    }

    public OrderDTO(Order entity) {
        this.id = entity.getId();
        this.moment = entity.getMoment();
        this.orderStatus = entity.getOrderStatus();
        this.clientId = entity.getClient().getId();
        this.clienteName = entity.getClient().getName();
        this.clenteEmail = entity.getClient().getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClienteName() {
        return clienteName;
    }

    public void setClienteName(String clienteName) {
        this.clienteName = clienteName;
    }

    public String getClenteEmail() {
        return clenteEmail;
    }

    public void setClenteEmail(String clenteEmail) {
        this.clenteEmail = clenteEmail;
    }

    public Order toEntity(){
        User client = new User(clientId,clienteName,clenteEmail,null,null);
        return new Order(id,moment,client,orderStatus);
    }
}

