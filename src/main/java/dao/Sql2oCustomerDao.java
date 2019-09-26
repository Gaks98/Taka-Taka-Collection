package dao;

import models.Estate;
import models.Customer;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;

public class Sql2oCustomerDao implements CustomerDao {

    private final Sql2o sql2o;

    public Sql2oCustomerDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    @Override
    public void add(Customer customer) {
        String sql = "INSERT INTO customers(name, location, address,estateId) VALUES (:name, :location,:address,:estateId)";
        try(Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql, true)
                    .bind(customer)
                    .executeUpdate()
                    .getKey();
            customer.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Customer findById(int id) {
        String sql = "SELECT * FROM customers WHERE id = :id";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Customer.class);
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customers";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql).executeAndFetch(Customer.class);
        }
    }

    @Override
    public List<Customer>getAllCustomersByEstate(int estateId){
        try (Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM customers WHERE estateId = :estateId")
                    .addParameter("estateId",estateId)
                    .executeAndFetch(Customer.class);
        }
    }

//    @Override
//    public List<Estate> getAllEstatesForCustomer(int customerId) {
//        ArrayList<Estate> allEstates = new ArrayList<>();
//        String matchToGetEstateId = "SELECT estateId FROM estates_customers WHERE customerId =:customerId";
//        try(Connection conn = sql2o.open()){
//            List<Integer> allEstateIds = conn.createQuery(matchToGetEstateId).addParameter("customerId", customerId)
//                    .executeAndFetch(Integer.class);
//            for(Integer estateId : allEstateIds){
//                String getFromEstates = "SELECT * FROM estates WHERE id=:estateId";
//                allEstates.add(conn.createQuery(getFromEstates).addParameter("estateId", estateId).executeAndFetchFirst(Estate.class));
//            }
//        }catch (Sql2oException ex){
//            System.out.println(ex);
//        }
//        return allEstates;
//    }

    @Override
    public void clearAll() {
        String sql = "DELETE from customers";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql).executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}

