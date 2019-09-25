package models;

import java.util.Objects;

public class Customer {
  private String name;
  private String location;
  private String address;
  private int estateId;
  private int id;

    public Customer(String name, String location, String address, int estateId) {
        this.name = name;
        this.location = location;
        this.address = address;
        this.estateId = estateId;
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getAddress() {
        return address;
    }

    public int getEstateId() {
        return estateId;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEstateId(int estateId) {
        this.estateId = estateId;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return estateId == customer.estateId &&
                id == customer.id &&
                Objects.equals(name, customer.name) &&
                Objects.equals(location, customer.location) &&
                Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, address, estateId, id);
    }


}
