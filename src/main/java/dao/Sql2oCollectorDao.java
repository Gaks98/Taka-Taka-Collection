package dao;

import models.Collector;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oCollectorDao implements CollectorDao{


    private final Sql2o sql2o;

    public  Sql2oCollectorDao(Sql2o sql2o){
        this.sql2o =sql2o;
    }

    @Override
    public void add(Collector collector) {
        String sql ="INSERT INTO collectors (firmName,estate,feeCharge,operationDay,disposalMode,recyclingSite,customersNumber) VALUES(:firmName,:estate,:feeCharge,:operationDay,:disposalMode,:recyclingSite,:customersNumber)";
        try(Connection conn=sql2o.open()){
            int id =(int) conn.createQuery(sql,true)
                    .bind(collector)
                    .executeUpdate()
                    .getKey();
            collector.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }



    @Override
    public List<Collector> getAll() {
       try(Connection conn =sql2o.open()){
           return conn.createQuery("SELECT * FROM collectors")
           .executeAndFetch(Collector.class);
       }
    }

    @Override
    public Collector findById(int id) {
        try(Connection conn =sql2o.open()){
            return conn.createQuery("SELECT *FROM collectors WHERE id=:id")
                    .addParameter("id",id)
                    .executeAndFetchFirst(Collector.class);
        }
    }

    @Override
    public void update(int id, String newFirmName, String newEstate, int newFeeCharge, String newOperationDay, String newDisposalMode, String newRecyclingSite, int newCustomersNumber) {
        String sql ="UPDATE collectors SET (firmName,estate,feeCharge,operationDay,disposalMode,recyclingSite,customersNumber) =(:firmName,:estate,:feeCharge,:operationDay,:disposalMode,:recyclingSite,:customersNumber) WHERE id=:id";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .addParameter("firmName",newFirmName)
                    .addParameter("estate",newEstate)
                    .addParameter("feeCharge",newFeeCharge)
                    .addParameter("operationDay",newOperationDay)
                    .addParameter("disposalMode",newDisposalMode)
                    .addParameter("recyclingSite",newRecyclingSite)
                    .addParameter("customersNumber",newCustomersNumber)
                    .addParameter("id",id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql ="DELETE  from collectors WHERE id= :id";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql ="DELETE from collectors";
        try(Connection conn =sql2o.open()) {
            conn.createQuery(sql)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
