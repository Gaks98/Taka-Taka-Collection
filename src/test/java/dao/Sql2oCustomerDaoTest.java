package dao;

import models.Customer;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oCustomerDaoTest {
    private Sql2oCustomerDao customerDao;
    private Sql2oEsateDao estateDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
//        estateDao = new Sql2oEsateDao(sql2o);
        customerDao = new Sql2oCustomerDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
        customerDao.clearAll();
//        estateDao.clearAll();
    }
//    @AfterClass
//    public void shutDown() throws Exception{
//        conn.close();
//    }

    @Test
    public void addToDBandSetId() {
        Customer customer = setupCustomer();
        customerDao.add(customer);
        int customerId= customer.getId();
        assertEquals(customer.getId(),customerId);
    }

    @Test
    public void findById() {
        Customer customer = setupCustomer();
        customerDao.add(customer);
        Customer customer1 = customerDao.findById(customer.getId());
        assertEquals(customer1,customer);
    }

    @Test
    public void getAllCustomer(){
        Customer customer = setupCustomer();
        customerDao.add(customer);
        Customer customer1 = setupCustomer();
        customerDao.add(customer1);
        assertTrue(customerDao.getAllCustomers().contains(customer));
        assertTrue(customerDao.getAllCustomers().contains(customer1));
    }

//    @Test
//    public void addCustomerToEstate() {
//        Customer customer = setupCustomer();
//        customerDao.add(customer);
//        Estate estate = new Estate("accounts", "Check balances", 20);
//        estateDao.add(estate);
//        Estate estate1 = new Estate("accounts", "Check balances", 20);
//        estateDao.add(estate1);
//        customerDao.addCustomerToEstate(customer,estate);
//        customerDao.addCustomerToEstate(customer,estate1);
//        Estate[] addedEmToEstate = {estate,estate1};
//        assertEquals(Arrays.asList(addedEmToEstate),customerDao.getAllEstatesForCustomer(customer.getId()));
//    }

    @Test
    public void clearAll() {
        Customer customer = setupCustomer();
        customerDao.add(customer);
        customerDao.clearAll();
        assertEquals(0,customerDao.getAllCustomers().size());
    }


    //helper method
    public Customer setupCustomer(){
        return new Customer("Jack", "Kanoo", "00100 Nairobi",1);
    }
}
