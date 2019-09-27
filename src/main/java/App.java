import Exceptions.ApiException;
import com.google.gson.Gson;
import dao.Sql2oCollectorDao;
import dao.Sql2oCustomerDao;
import models.Collector;
import models.Customer;
import org.sql2o.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        Sql2oCollectorDao collectorDao;
        Sql2oCustomerDao customerDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString ="jdbc:h2:~/Taka-Taka-Collection.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o =new Sql2o(connectionString,"","");

        collectorDao = new Sql2oCollectorDao(sql2o);
        customerDao = new Sql2oCustomerDao(sql2o);
        conn =sql2o.open();

        //routes for the layout project
        get("/",(request, response) -> {
            Map<String,Object>model = new HashMap<>();
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());


        post("/collectors/new","application/json",(request, response) -> {
            Collector collector =gson.fromJson(request.body(),Collector.class);
            collectorDao.add(collector);
            response.status(201);
            response.type("application/json");
            return gson.toJson(collector);
        });
        get("/collectors","application/json",(request, response) -> {
            response.type("application/json");
            return gson.toJson(collectorDao.getAll());
        });
        get("/collectors/:id","application/json",(request, response) -> {
            int collectorId = Integer.parseInt(request.params("id"));
            Collector collectorToFind = collectorDao.findById(collectorId);
            if(collectorToFind == null){
                throw new ApiException(404,String.format("Collector does not exist"));
            }else {
                response.type("application/json");
                return gson.toJson(collectorDao.findById(collectorId));
            }
        });


        //customer
        get("/new/customer",(req, response) -> {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model,"customerForm.hbs");
        },new HandlebarsTemplateEngine());

        post("/new/customer", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            String location = req.queryParams("location");
            String address = req.queryParams("address");
            int estateId = Integer.parseInt(req.queryParams("estateId"));
            Customer customer = new Customer(name,location,address,estateId);
            customerDao.add(customer);
            model.put("customer",customerDao.getAllCustomers());
            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine());

        //post:add a user(customers)
        post("/customer/new", "application/json", (request, response) -> {
            Customer customer = gson.fromJson(request.body(), Customer.class);
            customerDao.add(customer);
            response.type("application/json");
            response.status(201);
            return gson.toJson(customer);
        });

        //Get: View all users
        get("/customers", "application/json", (request, response) -> {
            return gson.toJson(customerDao.getAllCustomers());
        });

//        //post:Add a estate to a user
//        post("customer/customerId/estate/:estateId","application/json",(request, response) -> {
//            int emplyeeId = Integer.parseInt(request.params("customerId"));
//            int estateId = Integer.parseInt(request.params("estateId"));
//            Customer customerFound = customerDao.findById(emplyeeId);
//            Estate estatefound = estateDao.findById(estateId);
//
//            if (estatefound != null && customerFound != null){
//                estateDao.addEstateToCustomer(estatefound,customerFound);
//                response.type("application/json");
//                response.status(201);
//                return gson.toJson("Customer and Estate have been associated");
//            }
//            else {
//                throw new ApiException(404, String.format("customer or Estate does not exist"));
//            }
//        });

        //get:View all estates a user belongs to

        get("/customer/:customerId/estate","application/json",(request, response) -> {
            int customerId = Integer.parseInt(request.params("customerId"));
            Customer customer = customerDao.findById(customerId);

            if (customer == null){
                throw new Exception("No customer with that id");
            }else if(customerDao.getAllCustomersByEstate(customerId).size() == 0){
                return "{\"message\":\"Customer not associated with any estate\"}";
            }else {
                return gson.toJson(customerDao.getAllCustomersByEstate(customerId));
            }
        });


        exception(ApiException.class, (exc, req, res) -> {
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json"); //after does not run in case of an exception.
            res.status(err.getStatusCode()); //set the status
            res.body(gson.toJson(jsonMap));  //set the output.
        });
        //filter
    }
}
