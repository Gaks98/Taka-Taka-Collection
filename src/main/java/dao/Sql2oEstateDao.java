package dao;

import models.Estate;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;


import java.util.List;

public class Sql2oEstateDao implements EstateDao {

    private final Sql2o sql2o;
    public Sql2oEstateDao(Sql2o sql2o){ this.sql2o = sql2o; }


    @Override
    public void add(Estate estate) {
        String sql = "INSERT INTO estate (name,location,collector_id) VALUES (:name, :location, :collector_id)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(estate)
                    .executeUpdate()
                    .getKey();
            estate.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }
    @Override
    public Estate findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT *FROM estate WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Estate.class);
        }
    }

    @Override
    public List<Estate> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM estate")
                    .executeAndFetch(Estate.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from estate WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public void clearAll() {
        String sql = "DELETE from estate";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }
}
