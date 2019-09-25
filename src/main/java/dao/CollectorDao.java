package dao;

import models.Collector;

import java.util.List;

public interface CollectorDao {
    //create
    void add(Collector collector);

    //read
    List<Collector>getAll();

    //finding by id
    Collector findById(int id);

    //updating
    void update(int id,String firmName,String estate,int feeCharge,String operationDay,String disposalMode,String recyclingSite,int customersNumber);

    //deleting
    void deleteById( int id);
    void clearAll();

}
