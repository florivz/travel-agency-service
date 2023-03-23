package travel.travelagency.entities;

import java.util.Date;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonalDataTest {

  @Test()
  public void testEqualsMethodWithNull() {
    PersonalData emptyPersonalData = new PersonalData();
    PersonalData nullPersonalData = null;
    assertDoesNotThrow(() -> emptyPersonalData.equals(nullPersonalData));
    assertFalse(emptyPersonalData.equals(nullPersonalData));
  }

  @Test
  public void testEqualsMethodWithEmptyPersonalData() {
    PersonalData emptyPersonalData1 = new PersonalData();
    PersonalData emptyPersonalData2 = new PersonalData();
    assertDoesNotThrow(() -> emptyPersonalData1.equals(emptyPersonalData2));
    assertTrue(emptyPersonalData1.equals(emptyPersonalData2));
  }

  @Test
  public void testEqualsMethodWithIdenticalPersonalData() {
    PersonalData personalData1 = new PersonalData(
        43,
        "Merkel",
        "Angela",
        "Dorothea",
        new Date(1954, 07, 17),
        new Address(4, "Street", "18", "93726", "Town", "Country")
    );
    PersonalData personalData2 = new PersonalData(
        43,
        "Merkel",
        "Angela",
        "Dorothea",
        new Date(1954, 07, 17),
        new Address(4, "Street", "18", "93726", "Town", "Country")
    );

    assertDoesNotThrow(() -> personalData1.equals(personalData2));
    assertTrue(personalData1.equals(personalData2));
  }

  @Test
  public void testEqualsMethodWithDifferentPersonalData() {
    PersonalData personalData1 = new PersonalData(
        43,
        "Merkel",
        "Angela",
        "Dorothea",
        new Date(1954, 07, 17),
        new Address(4, "Street", "18", "93726", "Town", "Country")
    );
    PersonalData personalData2 = new PersonalData(
        98,
        "Scholz",
        "Olaf",
        "",
        new Date(1987, 11, 17),
        new Address(7, "Way", "9", "65958", "Stadt", "Osmanien")
    );
    assertDoesNotThrow(() -> personalData1.equals(personalData2));
    assertFalse(personalData1.equals(personalData2));
  }

  @Test
  public void testEqualsMethodWithDifferentClass() {
    PersonalData personalData1 = new PersonalData(
        43,
        "Merkel",
        "Angela",
        "Dorothea",
        new Date(1954, 07, 17),
        new Address(4, "Street", "18", "93726", "Town", "Country")
    );
    Object obj = new Object();
    assertDoesNotThrow(() -> personalData1.equals(obj));
    assertFalse(personalData1.equals(obj));
  }

  @Test
  public void testToStringMethod() {
    PersonalData personalData = new PersonalData(
      11,
      "Lustig",
      "Peter",
      "Fritz Willi",
      new Date(1937, 10, 20),
      new Address(
        175,
        "Street",
        "101a",
        "19824",
        "My Town",
        "Disneyland")
    );

    assertEquals("""
        Peter Fritz Willi Lustig, 20.10.1937
        Street 101a, 19824 My Town, Disneyland""",
        personalData.toString()
    );
  }

  @Test
  public void testHashCodeMethodWithIdenticalPersonalData() {
    int expectedHashCode = new PersonalData(
        98500,
        "Müller",
        "Thomas",
        "Juergen",
        new Date(1980, 11, 06),
        new Address()).hashCode();

    int actualHashCode = new PersonalData(
        98500,
        "Müller",
        "Thomas",
        "Juergen",
        new Date(1980, 11, 06),
        new Address()).hashCode();

    assertEquals(expectedHashCode, actualHashCode);
  }

  @Test
  public void testHashCodeMethodWithDifferentPersonalData() {
    int unexpectedHashCode = new PersonalData(
        915,
        "Rüttgers",
        "Thomas",
        "Elias",
        new Date(),
        new Address()).hashCode();

    int actualHashCode = new PersonalData(
        98500,
        "Müller",
        "Thomas",
        "Juergen",
        new Date(1980, 11, 06),
        new Address()).hashCode();

    assertNotEquals(unexpectedHashCode, actualHashCode);
  }

}
