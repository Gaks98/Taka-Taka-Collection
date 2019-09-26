package dao;

import models.Estate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2OEstateDaoTest {
    private Sql2oEstateDao estateDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        estateDao = new Sql2oEstateDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() {
        Estate estate =setupNewEstate();
        int originalEstateId = estate.getId();
        estateDao.add(estate);
        assertNotEquals(0,estate.getId());
    }
    @Test
    public void findById() {
        Estate estate = setupNewEstate();

        Estate foundEstate = estateDao.findById(estate.getId());
        assertEquals(estate,estateDao.findById(estate.getId()));

    }

    @Test
    public void getAll()throws Exception {
        Estate estate =setupNewEstate();

        assertEquals(1,estateDao.getAll().size());
    }

    @Test
    public void deleteById() {
        Estate estate =setupNewEstate();
        estateDao.deleteById(estate.getId());
    }

    @Test
    public void clearAll() throws Exception{
        Estate estate =setupNewEstate();
        Estate estate2 =setupNewEstate();
        estateDao.clearAll();
        assertEquals(0,estateDao.getAll().size());
    }
    public Estate setupNewEstate(){
        Estate testEstate = new Estate("Adams","karen",5);
        estateDao.add(testEstate);
        return testEstate;
    }

}