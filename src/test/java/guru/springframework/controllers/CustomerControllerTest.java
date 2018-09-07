package guru.springframework.controllers;

import guru.springframework.domain.Customer;
import guru.springframework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by jt on 11/17/15.
 */
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testList() throws Exception{
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        when(customerService.listAll()).thenReturn((List) customers);

        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("/list"))
                .andExpect(model().attribute("customers", hasSize(2)));
    }

    @Test
    public void testShow() throws Exception {
        Integer id = 1;

        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/show"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testEdit() throws Exception {
        Integer id = 1;

        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testNewCustomer() throws Exception {
        verifyZeroInteractions(customerService);

        mockMvc.perform(get("/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Integer id = 1;
        Customer returnCustomer = new Customer();
        String first = "Micheal";
        String last = "Weston";
        String address1 = "1 Main St";
        String address2 = "Apt 301";
        String city = "Miami";
        String state = "Florida";
        String zip = "33101";
        String email = "micheal@burnnotice.com";
        String phone = "305.333.0101";

        returnCustomer.setId(id);
        returnCustomer.setFirst(first);
        returnCustomer.setLast(last);
        returnCustomer.setAddress1(address1);
        returnCustomer.setAddress2(address2);
        returnCustomer.setCity(city);
        returnCustomer.setState(state);
        returnCustomer.setZip(zip);
        returnCustomer.setEmail(email);
        returnCustomer.setPhone(phone);

        when(customerService.saveOrUpdate(Matchers.any())).thenReturn(returnCustomer);

        mockMvc.perform(post("/customer")
                .param("id", "1")
                .param("first", first)
                .param("last", last)
                .param("address1", address1)
                .param("address2", address2)
                .param("city", city)
                .param("state", state)
                .param("zip", "33101")
                .param("email", email)
                .param("phone", phone))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:customer/show/1"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                .andExpect(model().attribute("customer", hasProperty("first", is(first))))
                .andExpect(model().attribute("customer", hasProperty("last", is(last))))
                .andExpect(model().attribute("customer", hasProperty("address1", is(address1))))
                .andExpect(model().attribute("customer", hasProperty("address2", is(address2))))
                .andExpect(model().attribute("customer", hasProperty("city", is(city))))
                .andExpect(model().attribute("customer", hasProperty("state", is(state))))
                .andExpect(model().attribute("customer", hasProperty("zip", is(zip))))
                .andExpect(model().attribute("customer", hasProperty("email", is(email))))
                .andExpect(model().attribute("customer", hasProperty("phone", is(phone))));

        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).saveOrUpdate(customerCaptor.capture());

        Customer boundCustomer = customerCaptor.getValue();

        assertEquals(id, boundCustomer.getId());
        assertEquals(first, boundCustomer.getFirst());
        assertEquals(last, boundCustomer.getLast());
        assertEquals(address1, boundCustomer.getAddress1());
        assertEquals(address2, boundCustomer.getAddress2());
        assertEquals(city, boundCustomer.getCity());
        assertEquals(state, boundCustomer.getState());
        assertEquals(zip, boundCustomer.getZip());
        assertEquals(email, boundCustomer.getEmail());
        assertEquals(phone, boundCustomer.getPhone());


    }
}
