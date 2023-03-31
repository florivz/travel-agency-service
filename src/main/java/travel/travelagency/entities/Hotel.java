package travel.travelagency.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "HOTEL")
public class Hotel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "HOTEL_ID")
  private Integer id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "PRICE_PER_PERSON")
  private Double pricePerPerson;

  @Column(name = "CURRENCY_KEY")
  private String currencyKey;

  @ManyToOne
  @JoinColumn(name = "ADDRESS_ID")
  private Address address;

  public Hotel() {

  }

  public Hotel(int id, String name, double pricePerPerson, String currencyKey, Address address) {
    this.id = id;
    this.name = name;
    this.pricePerPerson = pricePerPerson;
    this.currencyKey = currencyKey;
    this.address = address;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPricePerPerson() {
    return pricePerPerson;
  }

  public void setPricePerPerson(Double price) {
    this.pricePerPerson = price;
  }

  public String getCurrencyKey() {
    return currencyKey;
  }

  public void setCurrencyKey(String currency) {
    this.currencyKey = currency;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(this.getClass())) {
      Hotel hotel = (Hotel) obj;
      return
          ((id == null && hotel.getId() == null) || id.equals(hotel.getId())) &&
          ((name == null && hotel.getName() == null) || name.equals(hotel.getName())) &&
          ((pricePerPerson
              == null && hotel.getPricePerPerson() == null) || pricePerPerson.equals(hotel.getPricePerPerson())) &&
          ((currencyKey
              == null && hotel.getCurrencyKey() == null) || currencyKey.equals(hotel.getCurrencyKey())) &&
          ((address == null && hotel.getAddress() == null) || address.equals(hotel.getAddress()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        (name != null                             ? name.toString() + '\n'                                    : "" )
            + (address != null                    ? address.toString() + '\n'                                 : "" )
            + (pricePerPerson != null && currencyKey
            != null  ? "Price: " + pricePerPerson.toString() + " " + currencyKey.toString() : "" );
  }

  @Override
  public int hashCode() {
    return
        (String.valueOf(id != null  ? id.hashCode()       : null)
            + (name != null         ? name.hashCode()     : null)
            + (pricePerPerson != null        ? pricePerPerson.hashCode()    : null)
            + (currencyKey != null     ? currencyKey.hashCode() : null)
            + (address != null      ? address.hashCode()  : null)).hashCode();
  }

}
