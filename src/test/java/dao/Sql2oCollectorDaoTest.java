package dao;

import models.Collector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.*;
import static org.junit.Assert.*;

public class Sql2oCollectorDaoTest {
    private Sql2oCollectorDao collectorDao;
    private Connection conn;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        collectorDao = new Sql2oCollectorDao(sql2o);
        conn =sql2o.open();
    }


    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingCollectorSetsId() throws Exception{
        Collector testCollector= setupCollector();

        assertNotEquals(0,testCollector.getId());
    }
    @Test
    public void addedCollectorIsReturnedFromGetAll() throws Exception{
        Collector testCollector= setupCollector();

        assertEquals(1,collectorDao.getAll().size());
    }
    @Test
   public void noCollectorsReturnEmptyList() throws Exception{
        assertEquals(0,collectorDao.getAll().size());
    }
    @Test
    public void findByIdReturnsCorrectDepartment() throws Exception{
        Collector testCollector= setupCollector();

        Collector otherCollector= setupCollector();
        assertEquals(testCollector,collectorDao.findById(testCollector.getId()));
    }
    @Test
    public void updateCorrectlyUpdatesAllFields() throws Exception{
        Collector testCollector= setupCollector();

        collectorDao.update(testCollector.getId(),"Taka","Rangau",100,"Saturday","Reusing","Moringa",5);
        Collector foundCollector =collectorDao.findById(testCollector.getId());
        assertEquals("Taka",foundCollector.getFirmName());
        assertEquals("Rangau",foundCollector.getEstate());
        assertEquals(100,foundCollector.getFeeCharge());
        assertEquals("Saturday",foundCollector.getOperationDay());
        assertEquals("Reusing",foundCollector.getDisposalMode());
        assertEquals("Moringa",foundCollector.getRecyclingSite());
        assertEquals(5,foundCollector.getCustomersNumber());
    }
    @Test
    public void deleteByIdDeletesCorrectly() throws Exception{
        Collector testCollector= setupCollector();

        Collector otherCollector= setupCollector();
        collectorDao.deleteById(testCollector.getId());
        assertEquals(1,collectorDao.getAll().size());
    }
    @Test
    public void clearAll() throws Exception{
        Collector testCollector= setupCollector();
        Collector otherCollector =setupCollector();
        collectorDao.clearAll();
        assertEquals(0,collectorDao.getAll().size());
    }

    //helper method
    public Collector setupCollector(){
        Collector testCollector = new Collector("Taka-Taka-Collection","Rongai",300,"Tuesday","Burning","Rimpa",45);
        collectorDao.add(testCollector);
        return testCollector;
    }

}