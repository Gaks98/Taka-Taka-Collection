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
        Estate estate =setupNewEstate();
        int originalEstateId = estate.getId();
        estateDao.add(estate);
        assertNotEquals(originalEstateId,estate.getId());
    }
    @Test
    public void findById() {
        Estate estate = setupNewEstate();
        estateDao.add(estate);
        Estate foundEstate = estateDao.findById(estate.getId());
        assertEquals(estate, foundEstate);

    }

    @Test
    public void getAll()throws Exception {
        Estate estate =setupNewEstate();
        estateDao.add(estate);
        assertTrue(estateDao.getAll().contains(estate));
    }

    @Test
    public void deleteById() {
        Estate estate =setupNewEstate();
        estateDao.add(estate);
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
        return new Estate("Adams","karen",5);
    }

}