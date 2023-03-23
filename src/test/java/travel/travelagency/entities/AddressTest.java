package travel.travelagency.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

  @Test()
  public void testEqualsMethodWithNull() {
    Address emptyAddress = new Address();
    Address nullAddress = null;
    assertDoesNotThrow(() -> emptyAddress.equals(nullAddress));
    assertFalse(emptyAddress.equals(nullAddress));
  }

  @Test
  public void testEqualsMethodWithEmptyAddress() {
    Address emptyAddress1 = new Address();
    Address emptyAddress2 = new Address();
    assertDoesNotThrow(() -> emptyAddress1.equals(emptyAddress2));
    assertTrue(emptyAddress1.equals(emptyAddress2));
  }

  @Test
  public void testEqualsMethodWithIdenticalAddress() {
    Address address1 = new Address(4, "Street", "18", "93726", "Town", "Country");
    Address address2 = new Address(4, "Street", "18", "93726", "Town", "Country");
    assertDoesNotThrow(() -> address1.equals(address2));
    assertTrue(address1.equals(address2));
  }

  @Test
  public void testEqualsMethodWithDifferentAddress() {
    Address address1 = new Address(4, "Street", "18", "93726", "Town", "Country");
    Address address2 = new Address(7, "Dwy", "18b", "HKL21", "Town", "Ivy");
    assertDoesNotThrow(() -> address1.equals(address2));
    assertFalse(address1.equals(address2));
  }

  @Test
  public void testEqualsMethodWithDifferentClass() {
    Address address1 = new Address(4, "Street", "18", "93726", "Town", "Country");
    Object obj = new Object();
    assertDoesNotThrow(() -> address1.equals(obj));
    assertFalse(address1.equals(obj));
  }

  @Test
  public void testToStringMethod() {
    Address address = new Address(
      175,
      "Street",
      "101a",
      "19824",
      "My Town",
      "Disneyland");

    assertEquals(
      address.toString(),
      "Street 101a, 19824 My Town, Disneyland"
    );
  }

  @Test
  public void testHashCodeMethodWithIdenticalAddresses() {
    int expectedHashCode = new Address(
        9364,
        "Allee",
        "1e",
        "52888",
        "Burghausen",
        "Schlaraffenland").hashCode();

    int actualHashCode = new Address(
        9364,
        "Allee",
        "1e",
        "52888",
        "Burghausen",
        "Schlaraffenland").hashCode();

    assertEquals(expectedHashCode, actualHashCode);
  }

  @Test
  public void testHashCodeMethodWithDifferentAddresses() {
    int unexpectedHashCode = new Address(
        915,
        "Weg",
        "1e",
        "9158a",
        "Village",
        "Schlaraffenland").hashCode();

    int actualHashCode = new Address(
        98500,
        "Street",
        "1e",
        "32818",
        "Lil Town",
        "Schlaraffenland").hashCode();

    assertNotEquals(unexpectedHashCode, actualHashCode);
  }

}
