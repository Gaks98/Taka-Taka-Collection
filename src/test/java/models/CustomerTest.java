package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void CustomerInstantiatesCorrectly() throws Exception{
        Customer testCustomer = setupCustomer();
        assert(testCustomer instanceof Customer);
    }

    @Test
    public void getNameGetsName() {
        Customer testCustomer = setupCustomer();
        assertEquals("Jane",testCustomer.getName());
    }

    @Test
    public void getLocationGetsLocation() {
        Customer testCustomer = setupCustomer();
        assertEquals("Konaa",testCustomer.getLocation());
    }

    @Test
    public void getAddressGetsAddress() {
        Customer testCustomer = setupCustomer();
        assertEquals("00100 Nairobi",testCustomer.getAddress());
    }

    @Test
    public void getEstateId() {
        Customer testCustomer = setupCustomer();
        assertEquals(1,testCustomer.getEstateId());
    }

    // helper
    public Customer setupCustomer(){
        return new Customer("Jane","Konaa","00100 Nairobi",1);
    }
}