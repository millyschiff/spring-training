package guru.springframework.services;

import guru.springframework.domain.Customer;
import guru.springframework.domain.DomainObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.*;

@Service
public class CustomerServiceImpl extends AbstractMapService implements CustomerService{

    //private Map<Integer, Customer> customers;

    /*public CustomerServiceImpl(){
        loadCustomers();
    }*/

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Customer getById(Integer id){
        return (Customer) super.getById(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject){
        return (Customer) super.saveOrUpdate(domainObject);
    }

    /*private Integer getNextKey(){
        return Collections.max(customers.keySet()) + 1;
    }*/

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    protected void loadDomainObjects(){

        domainMap = new HashMap<>();

        Customer customer1 = new Customer();
        customer1.setFirst("Melissa");
        customer1.setLast("Schiff");
        customer1.setEmail(customer1.getFirst() + customer1.getLast() + "@levvel.io");
        customer1.setPhone("3157066462");
        customer1.setAddress1("542 Lorimer Street");
        customer1.setAddress2("Apt. 3L");
        customer1.setCity("Brooklyn");
        customer1.setState("NY");
        customer1.setZip(11211);
        customer1.setId(1);

        domainMap.put(1, customer1);

        Customer customer2 = new Customer();
        customer2.setFirst("Karen");
        customer2.setLast("Poberezkin");
        customer2.setEmail(customer1.getFirst() + customer1.getLast() + "@levvel.io");
        customer2.setPhone("3152893740");
        customer2.setAddress1("109 Whedon Road");
        customer2.setAddress2("Apt. 15");
        customer2.setCity("Syracuse");
        customer2.setState("NY");
        customer2.setZip(13219);
        customer2.setId(2);

        domainMap.put(2, customer2);

        Customer customer3 = new Customer();
        customer3.setFirst("Katherine");
        customer3.setLast("Baldwin");
        customer3.setEmail(customer1.getFirst() + customer1.getLast() + "@levvel.io");
        customer3.setPhone("3157066442");
        customer3.setAddress1("101 Fern Park Drive");
        customer3.setAddress2("");
        customer3.setCity("Camillus");
        customer3.setState("NY");
        customer3.setZip(13031);
        customer3.setId(3);

        domainMap.put(3, customer3);
    }
}

