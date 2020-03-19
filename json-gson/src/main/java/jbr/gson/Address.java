package jbr.gson;

public class Address {

  Address(String streetName, String doorNo, String city, String state, String country) {
    this.streetName = streetName;
    this.doorNo = doorNo;
    this.city = city;
    this.state = state;
    this.country = country;
  }

  private String streetName;
  private String doorNo;

  public String getStreetName() {
    return streetName;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public String getDoorNo() {
    return doorNo;
  }

  public void setDoorNo(String doorNo) {
    this.doorNo = doorNo;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  private String city;
  private String state;
  private String country;
}
