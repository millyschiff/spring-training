package guru.springframework.domain;

import javax.persistence.*;

@Entity
public class CartDetail extends AbstractDomainClass {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Integer id;
//
//    @Version
//    private Integer version;

    @ManyToOne
    private Cart cart;

    @OneToOne
    private Product product;

    private Integer quantity;

//    @Override
//    public Integer getId() {
//        return id;
//    }

//    @Override
//    public void setId(Integer id) {
//        this.id = id;
//    }

//    public Integer getVersion() {
//        return version;
//    }
//
//    public void setVersion(Integer version) {
//        this.version = version;
//    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
