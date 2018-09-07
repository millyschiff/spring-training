package guru.springframework.bootstrap;

import guru.springframework.domain.Address;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import guru.springframework.services.CustomerService;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private ProductService productService;
    private CustomerService customerService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadCustomers();

    }

    public void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirst("Micheal");
        customer1.setLast("Weston");

        customer1.setBilling(new Address());
        customer1.getBilling().setAddress1("1 Main St");
        customer1.getBilling().setCity("Miami");
        customer1.getBilling().setState("Florida");
        customer1.getBilling().setZip("33101");

        customer1.setEmail("micheal@burnnotice.com");
        customer1.setPhone("305.333.0101");
        customerService.saveOrUpdate(customer1);

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
        customerService.saveOrUpdate(customer2);

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
        customerService.saveOrUpdate(customer3);
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