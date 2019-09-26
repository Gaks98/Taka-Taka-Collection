package dao;

import models.Estate;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oEsateDao implements EstateDao {

    public Sql2oEsateDao(Sql2o sql2o) {

        this.sql2o =sql2o;
    }

    @Override
    public void add(Estate estate) {

    }

    @Override
    public Estate findById(int id) {
        return null;
    }

    @Override
    public List<Estate> getAll() {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
