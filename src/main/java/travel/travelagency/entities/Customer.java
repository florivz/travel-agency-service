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

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass().equals(this.getClass())) {
      Customer customer = (Customer) obj;
      return
          ((id == null && customer.getId() == null) || id.equals(customer.getId())) &&
          ((iban == null && customer.getIban() == null) || iban.equals(customer.getIban())) &&
          ((personalData == null && customer.getPersonalData() == null)
              || personalData.equals(customer.getPersonalData())) &&
          ((billingAddress == null && customer.getBillingAddress() == null)
              || billingAddress.equals(customer.getBillingAddress()));
    }
    return false;
  }

  @Override
  public String toString() {
    return
        (personalData != null ? personalData.toString() + '\n' : "" )
        + (iban != null ? iban + '\n' : "" )
        + (id != null ? "Customer Number: " + id.toString() + '\n' : "" )
        + (billingAddress != null ? "Billing Address: " + billingAddress.toString() : "" );
  }

  @Override
  public int hashCode() {
    return
        (String.valueOf(id != null ? id.hashCode() : null)
        + (iban != null ? iban.hashCode() : null)
        + (personalData != null ? personalData.hashCode() : null)
        + (billingAddress != null ? billingAddress.hashCode() : null)).hashCode();
  }

}
