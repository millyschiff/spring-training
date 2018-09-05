package guru.springframework.bootstrap;

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
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

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

    public void loadCustomers(){
        Customer customer1 = new Customer();
        customer1.setFirst("Melissa");
        customer1.setLast("Schiff");
        customer1.setAddress1("542 Lorimer Street");
        customer1.setAddress2("Apartment 3L");
        customer1.setCity("Brooklyn");
        customer1.setState("New York");
        customer1.setZip(11211);
        customer1.setEmail("melissa.schiff@levvel.io");
        customer1.setPhone("3157066462");
        customerService.saveOrUpdate(customer1);

        Customer customer2 = new Customer();
        customer2.setFirst("Karen");
        customer2.setLast("Schiff");
        customer2.setAddress1("109 Whedon Road");
        customer2.setAddress2("Apartment 15");
        customer2.setCity("Syracuse");
        customer2.setState("New York");
        customer2.setZip(13219);
        customer2.setEmail("karen.schiff@levvel.io");
        customer2.setPhone("3152893740");
        customerService.saveOrUpdate(customer2);

        Customer customer3 = new Customer();
        customer3.setFirst("Katherine");
        customer3.setLast("Baldwin");
        customer3.setAddress1("101 Fern Park Road");
        customer3.setCity("Camillus");
        customer3.setState("New York");
        customer3.setZip(13031);
        customer3.setEmail("katherine.baldwin@levvel.io");
        customer3.setPhone("3157064424");
        customerService.saveOrUpdate(customer3);
    }
}
