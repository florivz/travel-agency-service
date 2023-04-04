package travel.travelagency.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

/**
 * This class is a jpa entity to the corresponding table 'ADDRESS' in the database 'travel-agency-service_db'
 */
@Entity
@Table(name = "ADDRESS")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ADDRESS_ID")
  private Integer id;

  @Column(name = "STREET")
  private String street;

  @Column(name = "NUMBER")
  private String number;

  @Column(name = "ZIP")
  private String zipCode;

  @Column(name = "TOWN")
  private String town;

  @Column(name = "COUNTRY")
  private String country;

  public Address() { }

  public Address(
      Integer id, String street, String number,
      String zipCode, String town, String country
  ) {
    this.id = id;
    this.street = street;
    this.number = number;
    this.zipCode = zipCode;
    this.town = town;
    this.country = country;
  }

  public Integer getId() {
    return id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getTown() {
    return town;
  }

  public void setTown(String town) {
    this.town = town;
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
        ((street == null && address.getStreet() == null) || street.equals(address.getStreet())) &&
        ((number == null && address.getNumber() == null) || number.equals(address.getNumber())) &&
        ((zipCode == null && address.getZipCode() == null) || zipCode.equals(address.getZipCode())) &&
        ((town == null && address.getTown() == null) || town.equals(address.getTown())) &&
        ((country == null && address.getCountry() == null) || country.equals(address.getCountry()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        street +
        " " +
            number +
        ", " +
        zipCode +
        " " +
            town +
        ", " +
        country;
  }

  @Override
  public int hashCode() {
    String appendedHashCodes = String.valueOf(id != null ? id.hashCode() : null)
        + (street != null ? street.hashCode() : null)
        + (number != null ? number.hashCode() : null)
        + (zipCode != null ? zipCode.hashCode() : null)
        + (town != null ? town.hashCode() : null)
        + (country != null ? country.hashCode() : null);
    return appendedHashCodes.hashCode();
  }

}
