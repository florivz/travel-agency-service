package travel.travelagency.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "TRAVELLER")
public class Traveller {

  @Id
  @Column(name = "PASSPORT_ID")
  private String passportID;

  @Column(name = "PLACE_OF_BIRTH")
  private String placeOfBirth;

  @OneToMany
  @JoinColumn(name = "PERSONAL_DATA_ID")
  @Column(name = "PERSONAL_DATA_ID")
  private PersonalData personalData;

  public Traveller() { }

  public Traveller(String passportID, String placeOfBirth, PersonalData personalData) {
    this.passportID = passportID;
    this.placeOfBirth = placeOfBirth;
    this.personalData = personalData;
  }

  public String getPassportID() {
    return passportID;
  }

  public void setPassportID(String passportID) {
    this.passportID = passportID;
  }

  public String getPlaceOfBirth() {
    return placeOfBirth;
  }

  public void setPlaceOfBirth(String placeOfBirth) {
    this.placeOfBirth = placeOfBirth;
  }

  public PersonalData getPersonalData() {
    return personalData;
  }

  public void setPersonalData(PersonalData personalData) {
    this.personalData = personalData;
  }
}