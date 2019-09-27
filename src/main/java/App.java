import Exceptions.ApiException;
import com.google.gson.Gson;
import dao.Sql2oCollectorDao;
import models.Collector;
import org.sql2o.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        Sql2oCollectorDao collectorDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString ="jdbc:h2:~/Taka-Taka-Collection.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o =new Sql2o(connectionString,"","");

        collectorDao = new Sql2oCollectorDao(sql2o);
        conn =sql2o.open();

        //routes for the layout project
        get("/",(request, response) -> {
            Map<String,Object>model = new HashMap<>();
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        get("/new/collector",(request, response) -> {
            Map<String,Object>model =new HashMap<>();
            return new ModelAndView(model,"collectorForm.hbs");
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
        exception(ApiException.class, (exc, req, res) -> {
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json"); //after does not run in case of an exception.
            res.status(err.getStatusCode()); //set the status
            res.body(gson.toJson(jsonMap));  //set the output.
        });

        post("/collectors/new",(request, response) -> {
            Map<String,Object>model= new HashMap<>();
            String firmName =request.queryParams("firmName");
            String estate = request.queryParams("estate");
            int feeCharge =Integer.parseInt(request.queryParams("feeCharge"));
            String operationDay = request.queryParams("operationDay");
            String disposalMode = request.queryParams("disposalMode");
            String recyclingSite = request.queryParams("recyclingSite");
            int customerNumber = Integer.parseInt(request.queryParams("customersNumber"));
            Collector collector1=new Collector(firmName,estate,feeCharge,operationDay,disposalMode,recyclingSite,customerNumber);
            model.put("collector",collector1);
            return new ModelAndView(model,"display.hbs");
        },new HandlebarsTemplateEngine());
    }
}
