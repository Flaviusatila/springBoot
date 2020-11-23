package com.iftm.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iftm.course.entities.pk.OrderItenPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_order_item")
public class OrderIten implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderItenPK id = new OrderItenPK();

    private Integer quantity;
    private Double price;

    public OrderIten(Order ordem, Product product, Integer quantity, Double price) {
        super();
        id.setOrder( ordem );
        id.setProduct( product );
        this.quantity = quantity;
        this.price = price;
    }

    public OrderIten() {
    }

    @JsonIgnore
    public Order getOrder(){
        return id.getOrder();
    }

    public void setOrder(Order order){
         id.setOrder(order);
    }

    public Product getProduct(){
        return id.getProduct();
    }

    public void setProduct(Product product){
        id.setProduct(product);
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public Double getPrice() {
        return this.price;
    }


    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubTotal(){
        return price * quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderIten orderIten = ( OrderIten ) o;
        return Objects.equals( id, orderIten.id );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id );
    }
}
