import com.google.gson.Gson;
import dao.Sql2oCollectorDao;
import models.Collector;
import org.sql2o.*;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        Sql2oCollectorDao collectorDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString ="jdbc:h2:~/Taka-Taka-Collection.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o =new Sql2o(connectionString,"","");

        collectorDao = new Sql2oCollectorDao(sql2o);
        conn =sql2o.open();

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
    }
}
