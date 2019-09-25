package models;

public class Estate {
    private int id;
    private String name;
    private String location;
    private int collector_id;

  public Estate(String name,String location,int collector_id){
      this.name=name;
      this.location=location;
      this.collector_id=collector_id;


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
}
