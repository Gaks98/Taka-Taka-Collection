package dao;

import models.Estate;

import java.util.List;

public interface EstateDao {

    void  add(Estate estate);

    Estate findById(int id);

    List<Estate> getAll();
    void deleteById(int id);
    void clearAll();

}
