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
    Collector testCollector = setupCollector();
    @Test
    public void collectorInstantiatesCorrectly() throws  Exception{
        assertEquals(true,testCollector instanceof Collector);
    }
    @Test
    public void getFirmNameHasAFirmName() throws Exception{
        assertEquals("Taka-Taka-Collection",testCollector.getFirmName());
    }
    @Test
    public void getEstateGetsTheEsate() throws Exception{
        assertEquals("Rongai",testCollector.getEstate());
    }
    @Test
    public void getFeeChargeReturnsTheFeeCharged() throws Exception{
        assertEquals(300,testCollector.getFeeCharge());
    }
    @Test
    public void getOperationsReturnsTheDayOfOperation() throws Exception{
        assertEquals("Tuesday",testCollector.getOperationDay());
    }
    @Test
    public void getDisposalModeReturnTheDisposalMode() throws Exception{
        assertEquals("Burning",testCollector.getDisposalMode());
    }
    @Test
    public void getRecyclingSiteReturnTheRecyclingSite() throws Exception{
        assertEquals("Rimpa",testCollector.getRecyclingSite());
    }
    @Test
    public void getCustomerNumberGetsTheCustomerNumber() throws Exception{
        assertEquals(45,testCollector.getCustomersNumber());
    }

    //helper methods
    public Collector setupCollector(){
        return new Collector("Taka-Taka-Collection","Rongai",300,"Tuesday","Burning","Rimpa",45);
    }
}