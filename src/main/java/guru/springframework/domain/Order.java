package guru.springframework.domain;

import org.springframework.data.domain.jaxb.SpringDataJaxb;
import guru.springframework.enums.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDER_HEADER")
public class Order extends AbstractDomainClass {

    @OneToOne
    private Customer customer;

    @Embedded
    private Address shiptoAddress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    private OrderStatus orderStatus;
    private Date dateshipped;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getShiptoAddress() {
        return shiptoAddress;
    }

    public void setShiptoAddress(Address shiptoAddress) {
        this.shiptoAddress = shiptoAddress;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void addToOrderDetails(OrderDetail orderDetail){
        orderDetail.setOrder(this);
        orderDetails.add(orderDetail);
    }

    public void removeOrderDetail(OrderDetail orderDetail){
        orderDetail.setOrder(null);
        orderDetails.remove(orderDetail);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDateshipped() {
        return dateshipped;
    }

    public void setDateshipped(Date dateshipped) {
        this.dateshipped = dateshipped;
    }
}
