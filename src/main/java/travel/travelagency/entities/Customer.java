package travel.travelagency.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CUSTOMER_ID")
  private Integer id;

  @Column(name = "IBAN")
  private String iban;

  @OneToMany
  @JoinColumn(name = "PERSONAL_DATA_ID")
  @Column(name = "PERSONAL_DATA_ID")
  private PersonalData personalData;

  @OneToMany
  @JoinColumn(name = "BILLING_ADDRESS_ID")
  @Column(name = "BILLING_ADDRESS_ID")
  private Address billingAddress;

  public Customer() { }

  public Customer(Integer id, String iban, PersonalData personalData, Address billingAddress) {
    this.id = id;
    this.iban = iban;
    this.personalData = personalData;
    this.billingAddress = billingAddress;
  }

  public Integer getId() {
    return id;
  }

  public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public PersonalData getPersonalData() {
    return personalData;
  }

  public void setPersonalData(PersonalData personalData) {
    this.personalData = personalData;
  }

  public Address getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(Address billingAddress) {
    this.billingAddress = billingAddress;
  }
}
