package com.iftm.course.entities;

import com.iftm.course.entities.enums.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {


    private static final long serialVersionUID = 2427988136947317999L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant moment;

    private Integer orderStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;


    @OneToMany(mappedBy = "id.order")
    private Set<OrderIten> itens = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;



    public Order(Long id, Instant moment, User user, OrderStatus orderStatus) {
        super();
        this.id = id;
        this.moment = moment;
        this.client = user;
        setOrderStatus( orderStatus );
    }

    public Order() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Order;
    }

    public Long getId() {
        return this.id;
    }

    public Set<OrderIten> getItens() {
        return itens;
    }

    public Instant getMoment() {
        return this.moment;
    }

    public User getClient() {
        return this.client;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf( orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null)
        this.orderStatus = orderStatus.getCode();
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Double getTotal(){
        double sum = 0.0;
        for (OrderIten x : itens){
            sum += x.getSubTotal();
        }
        return sum;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Order)) return false;
        final Order other = ( Order ) o;
        if (!other.canEqual( ( Object ) this )) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals( other$id )) return false;
        final Object this$moment = this.getMoment();
        final Object other$moment = other.getMoment();
        if (this$moment == null ? other$moment != null : !this$moment.equals( other$moment )) return false;
        final Object this$client = this.getClient();
        final Object other$client = other.getClient();
        if (this$client == null ? other$client != null : !this$client.equals( other$client )) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $moment = this.getMoment();
        result = result * PRIME + ($moment == null ? 43 : $moment.hashCode());
        final Object $client = this.getClient();
        result = result * PRIME + ($client == null ? 43 : $client.hashCode());
        return result;
    }

    public String toString() {
        return "Order(id=" + this.getId() + ", moment=" + this.getMoment() + ", client=" + this.getClient() + ")";
    }
}
