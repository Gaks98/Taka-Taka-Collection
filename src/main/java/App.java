import Exceptions.ApiException;
import com.google.gson.Gson;
import dao.Sql2oCollectorDao;
import models.Collector;
import dao.Sql2oEsateDao;
import models.Estate;
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
        Sql2oEsateDao estateDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString ="jdbc:h2:~/Taka-Taka-Collection.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o =new Sql2o(connectionString,"","");
        estateDao = new Sql2oEsateDao(sql2o);
        collectorDao = new Sql2oCollectorDao(sql2o);
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
        post("/estate/new","application/json",(request, response) -> {
            Estate estate =gson.fromJson(request.body(),Estate.class);
            estateDao.add(estate);
            response.status(201);
            response.type("application/json");
            return gson.toJson(estate);
        });
        get("/estate","application/json",(request, response) -> {
            response.type("application/json");
            return gson.toJson(estateDao.getAll());
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
        after((request, response) -> {
            response.type("application/json");
        });
    }
}
