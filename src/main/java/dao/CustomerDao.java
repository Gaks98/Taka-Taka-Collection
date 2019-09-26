package dao;
import models.Customer;
import models.Estate;

import java.util.List;
public interface CustomerDao {
    //create
    void add(Customer customer);

    //Find a user by the id
    Customer findById(int id);
    //read
    List<Customer> getAllCustomers();
    List<Customer> getAllCustomersByEstate(int estateId);


    //delete
//    void deleteById(int id);
    void clearAll();
}