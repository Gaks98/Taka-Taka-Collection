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
    public void getE_name() {
        Estate testEstate = setupEstate();
        assertEquals("Moringa", testEstate.getE_name());
    }
    @Test
    public void getE_location() {
        Estate testEstate = setupEstate();
        assertEquals("Kilimani", testEstate.getE_location());
    }
    @Test
    public void getE_collector_id() {
        Estate testEstate = setupEstate();
        assertEquals(5, testEstate.getE_collector_id());
    }

    public Estate setupEstate(){
        return new Estate("Moringa","Kilimani",5);
    }
}