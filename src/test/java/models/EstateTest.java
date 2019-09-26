package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EstateTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void estateInstantiatesCorrectly() throws Exception{
        Estate testEstate = setupEstate();
        assertTrue(testEstate instanceof Estate);
    }
    @Test
    public void getName() {
        Estate testEstate = setupEstate();
        assertEquals("Adams", testEstate.getName());
    }


    @Test
    public void getLocation() {
        Estate testEstate = setupEstate();
        assertEquals("karen", testEstate.getLocation());
    }
    @Test
    public void getCollector_id() {
        Estate testEstate = setupEstate();
        assertEquals(5, testEstate.getCollector_id());
    }

    public Estate setupEstate(){
        return new Estate("Adams","karen",5);
    }
}