package travel.travelagency.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table(name = "ADDRESS")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ADDRESS_ID")
  private Integer id;

  @Column(name = "STREET")
  private String streetName;

  @Column(name = "NUMBER")
  private String houseNumber;

  @Column(name = "ZIP")
  private String zipCode;

  @Column(name = "TOWN")
  private String townName;

  @Column(name = "COUNTRY")
  private String country;

  public Address() { }

  public Address(
      Integer id, String streetName, String houseNumber,
      String zipCode, String townName, String country
  ) {
    this.id = id;
    this.streetName = streetName;
    this.houseNumber = houseNumber;
    this.zipCode = zipCode;
    this.townName = townName;
    this.country = country;
  }

  public Integer getId() {
    return id;
  }

  public String getStreetName() {
    return streetName;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public String getHouseNumber() {
    return houseNumber;
  }

  public void setHouseNumber(String houseNumber) {
    this.houseNumber = houseNumber;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getTownName() {
    return townName;
  }

  public void setTownName(String townName) {
    this.townName = townName;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(this.getClass())) {
      Address address = (Address) obj;
      return
        ((id == null && address.getId() == null) || id.equals(address.getId())) &&
        ((streetName == null && address.getStreetName() == null) || streetName.equals(address.getStreetName())) &&
        ((houseNumber == null && address.getHouseNumber() == null) || houseNumber.equals(address.getHouseNumber())) &&
        ((zipCode == null && address.getZipCode() == null) || zipCode.equals(address.getZipCode())) &&
        ((townName == null && address.getTownName() == null) || townName.equals(address.getTownName())) &&
        ((country == null && address.getCountry() == null) || country.equals(address.getCountry()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        streetName +
        " " +
        houseNumber +
        ", " +
        zipCode +
        " " +
        townName +
        ", " +
        country;
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

}
