package models;

import java.util.Objects;

public class Estate {
    private int id;
    private String name;
    private String location;
    private int collector_id;

    public Estate(String name, String location, int collector_id){

        this.name=name;
        this.location=location;
        this.collector_id=collector_id;


    }

    public void setCollector_id(int collector_id) {
        this.collector_id = collector_id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }


    public void setLocation(String location) {
        this.location = location;
    }

    public String getE_name() {
      return name;
    }

    public String getE_location() {
      return location;

    }

    public int getE_collector_id() {
      return collector_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)return true;
        if(o==null||getClass()!=o.getClass())return false;

        Estate estate =(Estate)o;
        return getE_collector_id()== estate.getE_collector_id()&&
                getE_name().equals(estate.getE_name()) &&
                getE_location().equals(estate.getE_location());
    }
    @Override
    public int hashCode() {

            return Objects.hash(getE_name(), getE_name(), getE_collector_id());

    }
}
