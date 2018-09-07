package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.enums.OrderStatus;
import guru.springframework.services.CustomerService;
import guru.springframework.services.ProductService;
import guru.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import sun.plugin.perf.PluginRollup;

import java.math.BigDecimal;
import java.util.List;


@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private ProductService productService;
    private UserService userService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadUsersAndCustomers();
        loadCarts();
        loadOrderHistory();

    }

    private void loadOrderHistory() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            Order order = new Order();
            order.setCustomer(user.getCustomer());
            order.setOrderStatus(OrderStatus.SHIPPED);

            products.forEach(product -> {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(1);
                order.addToOrderDetails(orderDetail);
            });
        });

    }

    private void loadCarts() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            user.setCart(new Cart());
            CartDetail cartDetail = new CartDetail();
            cartDetail.setProduct(products.get(0));
            cartDetail.setQuantity(2);
            user.getCart().addCartDetail(cartDetail);
            userService.saveOrUpdate(user);
        });
    }

    public void loadUsersAndCustomers() {
        User user1 = new User();
        user1.setUsername("mweston");
        user1.setPassword("password");
        Customer customer1 = new Customer();
        customer1.setFirst("Michael");
        customer1.setLast("Weston");
        customer1.setBilling(new Address());
        customer1.getBilling().setAddress1("1 Main St");
        customer1.getBilling().setCity("Miami");
        customer1.getBilling().setState("Florida");
        customer1.getBilling().setZip("33101");
        customer1.setEmail("micheal@burnnotice.com");
        customer1.setPhone("305.333.0101");
        user1.setCustomer(customer1);
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUsername("fglenanne");
        user2.setPassword("password");
        Customer customer2 = new Customer();
        customer2.setFirst("Fiona");
        customer2.setLast("Glenanne");
        customer2.setBilling(new Address());
        customer2.getBilling().setAddress1("1 Main St");
        customer2.getBilling().setCity("Miami");
        customer2.getBilling().setState("Florida");
        customer2.getBilling().setZip("33101");
        customer2.setEmail("fiona@burnnotice.com");
        customer2.setPhone("305.323.0233");
        user2.setCustomer(customer2);
        userService.saveOrUpdate(user2);

        User user3 = new User();
        user3.setUsername("mweston");
        user3.setPassword("password");
        Customer customer3 = new Customer();
        customer3.setFirst("Sam");
        customer3.setLast("Axe");
        customer3.setBilling(new Address());
        customer3.getBilling().setAddress1("1 Main St");
        customer3.getBilling().setCity("Miami");
        customer3.getBilling().setState("Florida");
        customer3.getBilling().setZip("33101");
        customer3.setEmail("sam@burnnotice.com");
        customer3.setPhone("305.426.9832");
        user3.setCustomer(customer3);
        userService.saveOrUpdate(user3);
    }

    public void loadProducts(){

        Product product1 = new Product();
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("12.99"));
        product1.setImageUrl("http://example.com/product1");
        productService.saveOrUpdate(product1);

        Product product2 = new Product();
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("14.99"));
        product2.setImageUrl("http://example.com/product2");
        productService.saveOrUpdate(product2);

        Product product3 = new Product();
        product3.setDescription("Product 3");
        product3.setPrice(new BigDecimal("34.99"));
        product3.setImageUrl("http://example.com/product3");
        productService.saveOrUpdate(product3);

        Product product4 = new Product();
        product4.setDescription("Product 4");
        product4.setPrice(new BigDecimal("44.99"));
        product4.setImageUrl("http://example.com/product4");
        productService.saveOrUpdate(product4);

        Product product5 = new Product();
        product5.setDescription("Product 5");
        product5.setPrice(new BigDecimal("25.99"));
        product5.setImageUrl("http://example.com/product5");
        productService.saveOrUpdate(product5);

    }
}