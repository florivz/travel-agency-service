package travel.travelagency.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumns;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "HOTEL")
public class Hotel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "HOTEL_ID")
  private int id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "PRICE_PER_PERSON")
  private double price;

  @Column(name = "CURRENCY_KEY")
  private String currency;

  @OneToMany
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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
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
}
