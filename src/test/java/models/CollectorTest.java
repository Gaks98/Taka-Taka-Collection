package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CollectorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void collectorInstantiatesCorrectly() throws  Exception{
        Collector testCollector = setupCollector();
        assertEquals(true,testCollector instanceof Collector);
    }

    //helper methods
    public Collector setupCollector(){
        return new Collector("Taka-Taka-Collection","Rongai",300,"Tuesday","Burning","Rimpa",45);
    }
}