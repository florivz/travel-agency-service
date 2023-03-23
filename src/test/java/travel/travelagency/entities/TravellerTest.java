package travel.travelagency.entities;

import java.util.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TravellerTest {

  private static Traveller nullTraveller, emptyTraveller, traveller, copyTraveller, differentTraveller;

  @BeforeAll
  public static void initializeCustomers() {
    nullTraveller = null;
    emptyTraveller = new Traveller();
    traveller = new Traveller(
        "C12345678",
        "Berlin",
        new PersonalData(
            43,
            "Merkel",
            "Angela",
            "Dorothea",
            new Date(1954, 07, 17),
            new Address(4, "Street", "18", "93726", "Town", "Country")
        )
    );
    copyTraveller = new Traveller(
        "C12345678",
        "Berlin",
        new PersonalData(
            43,
            "Merkel",
            "Angela",
            "Dorothea",
            new Date(1954, 07, 17),
            new Address(4, "Street", "18", "93726", "Town", "Country")
        )
    );
    differentTraveller = new Traveller(
        "D01928345",
        "Hamburg",
        new PersonalData(
            98,
            "Scholz",
            "Olaf",
            "",
            new Date(1987, 11, 17),
            new Address(7, "Way", "9", "65958", "Stadt", "Osmanien")
        )
    );
  }

  @Test()
  public void testEqualsMethodWithNull() {
    assertDoesNotThrow(() -> emptyTraveller.equals(nullTraveller));
    assertFalse(emptyTraveller.equals(nullTraveller));
  }

  @Test
  public void testEqualsMethodWithEmptyTraveller() {
    Traveller copyEmptyTraveller = new Traveller();
    assertDoesNotThrow(() -> emptyTraveller.equals(copyEmptyTraveller));
    assertTrue(emptyTraveller.equals(copyEmptyTraveller));
  }

  @Test
  public void testEqualsMethodWithIdenticalTraveller() {
    assertDoesNotThrow(() -> traveller.equals(copyTraveller));
    assertTrue(traveller.equals(copyTraveller));
  }

  @Test
  public void testEqualsMethodWithDifferentTraveller() {
    assertDoesNotThrow(() -> traveller.equals(differentTraveller));
    assertFalse(traveller.equals(differentTraveller));
  }

  @Test
  public void testEqualsMethodWithDifferentClass() {
    Object obj = new Object();
    assertDoesNotThrow(() -> traveller.equals(obj));
    assertFalse(traveller.equals(obj));
  }

  @Test
  public void testToStringMethod() {
    Traveller peterLustig = new Traveller(
        "C98765432",
        "Bärstadt",
        new PersonalData(
            43,
            "Lustig",
            "Peter",
            "Fritz Willi",
            new Date(1937, 10, 20),
            new Address(19, "Street", "101a", "19824", "My Town", "Disneyland")
        )
    );

    assertEquals("""
        Peter Fritz Willi Lustig, 20.10.1937
        Street 101a, 19824 My Town, Disneyland
        Passport Number: C98765432
        Place of Birth : Bärstadt""",
        peterLustig.toString()
    );
  }

  @Test
  public void testHashCodeMethodWithIdenticalTraveller() {
    int expectedHashCode  = traveller.hashCode();
    int actualHashCode    = copyTraveller.hashCode();

    assertEquals(expectedHashCode, actualHashCode);
  }

  @Test
  public void testHashCodeMethodWithDifferentTraveller() {
    int unexpectedHashCode  = traveller.hashCode();
    int actualHashCode      = differentTraveller.hashCode();

    assertNotEquals(unexpectedHashCode, actualHashCode);
  }

}