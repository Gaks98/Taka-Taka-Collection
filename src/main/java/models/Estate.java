package models;

import java.util.Objects;

public class Estate {
    private int id;
    private String name;
    private String location;
    private int collector_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estate estate = (Estate) o;
        return getId() == estate.getId() &&
                getCollector_id() == estate.getCollector_id() &&
                Objects.equals(getName(), estate.getName()) &&
                Objects.equals(getLocation(), estate.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLocation(), getCollector_id());
    }

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

    public String getName() {
      return name;
    }

    public String getLocation() {
      return location;

    }

    public int getCollector_id() {
      return collector_id;
    }



    public int getId() {
        return id;
    }
}
