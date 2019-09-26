package dao;

import models.Estate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oEsateDaoTest {
    private Sql2oEsateDao estateDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        estateDao = new Sql2oEsateDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void clearAll() {
    }
    public Estate setupEstate(){
        return new Estate("Moringa","Kilimani",5);
    }

}