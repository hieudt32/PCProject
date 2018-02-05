package app.positiveculture.com.data.response.dto;

/**
 * Address
 * Created by BB on 8/29/2017.
 */

public class Address {

  private String floor;
  private String unit;
  private String building;
  private String street;
  private String street2;
  private String country;
  private String postalCode;

  public Address() {
  }

  public Address(String floor, String building, String street, String street2, String country, String postalCode) {
    this.floor = floor;
    this.building = building;
    this.street = street;
    this.street2 = street2;
    this.country = country;
    this.postalCode = postalCode;
  }

  public String getFloor() {
    return floor;
  }

  public void setFloor(String floor) {
    this.floor = floor;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public String getBuilding() {
    return building;
  }

  public void setBuilding(String building) {
    this.building = building;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getStreet2() {
    return street2;
  }

  public void setStreet2(String street2) {
    this.street2 = street2;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
}
