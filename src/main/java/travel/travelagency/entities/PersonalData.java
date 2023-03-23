package travel.travelagency.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "PERSONAL_DATA")
public class PersonalData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PERSONAL_DATA_ID")
  private Integer id;

  @Column(name = "LAST_NAME")
  private String lastName;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "MIDDLE_NAMES")
  private String middleNames;

  @Column(name = "DATE_OF_BIRTH")
  private Date dateOfBirth;

  @OneToMany
  @JoinColumn(name = "ADDRESS_ID")
  @Column(name = "ADDRESS_ID")
  private Address address;

  public PersonalData() { }

  public PersonalData(Integer id, String lastName, String firstName, String middleNames, Date dateOfBirth, Address address) {
    this.id = id;
    this.lastName = lastName;
    this.firstName = firstName;
    this.middleNames = middleNames;
    this.dateOfBirth = dateOfBirth;
    this.address = address;
  }

  public Integer getId() {
    return id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleNames() {
    return middleNames;
  }

  public void setMiddleNames(String middleNames) {
    this.middleNames = middleNames;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
