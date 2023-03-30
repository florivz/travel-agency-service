package travel.travelagency.entities;

import java.util.Currency;
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
  private Double price;

  @Column(name = "CURRENCY_KEY")
  private String currency;

  @ManyToOne
  @JoinColumn(name = "ADDRESS_ID")
  private Address address;

  public Hotel() {

  }

  public Hotel(int id, String name, double price, String currency, Address address) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.currency = currency;
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

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
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
          ((price == null && hotel.getPrice() == null) || price.equals(hotel.getPrice())) &&
          ((currency == null && hotel.getCurrency() == null) || currency.equals(hotel.getCurrency())) &&
          ((address == null && hotel.getAddress() == null) || address.equals(hotel.getAddress()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        (name != null                             ? name.toString() + '\n'                                    : "" )
            + (address != null                    ? address.toString() + '\n'                                 : "" )
            + (price != null && currency != null  ? "Price: " + price.toString() + " " + currency.toString() : "" );
  }

  @Override
  public int hashCode() {
    return
        (String.valueOf(id != null  ? id.hashCode()       : null)
            + (name != null         ? name.hashCode()     : null)
            + (price != null        ? price.hashCode()    : null)
            + (currency != null     ? currency.hashCode() : null)
            + (address != null      ? address.hashCode()  : null)).hashCode();
  }

}
